package com.wen.seckill.service.Impl;

import com.wen.seckill.constant.Constants;
import com.wen.seckill.domian.SeckillOrder;
import com.wen.seckill.exception.CheckException;
import com.wen.seckill.rabbitmq.MQSender;
import com.wen.seckill.rabbitmq.SeckillMessage;
import com.wen.seckill.redis.RedisService;
import com.wen.seckill.service.OrdersService;
import com.wen.seckill.service.ProductService;
import com.wen.seckill.service.SeckillService;
import com.wen.seckill.utils.JsonUtils;
import com.wen.seckill.utils.RequestAndResponseUtils;
import com.wen.seckill.vo.OrderInfo;
import com.wen.seckill.vo.Product;
import com.wen.seckill.vo.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/9/17  13:55
 */
@Service
@Slf4j
public class SeckillServiceImpl implements SeckillService {

    AtomicInteger atomicInteger= new AtomicInteger();

    @Autowired
    RedisService redisService;
    @Autowired
    ProductService productService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    MQSender mqSender;

    //本地内存，立刻更新内存标记，让所有线程立即可见。单机版的秒杀可以这么玩，分布式环境下就别这么玩了
    private volatile Map<Long,Object> map = new HashMap<>();

    /**
     * 流程：
     * 判断商品是否存在
     * 判断本地内存中商品是否还有
     * 判断是否重复秒杀
     * 判断redis是否还有内存
     * 将秒杀信息交给mq做异步下单
     * @param productId
     * @return
     */
    @Override
    public String doSeckill(long productId) {
        //这个是线程安全的
        HttpServletRequest request= RequestAndResponseUtils.getRequest();
        Users users = (Users) request.getAttribute(Constants.USER_SESSION);
        //判断本地内存中是否有这件商品
       boolean flag= map.containsKey(productId);
        if (!flag){
            throw  new CheckException("商品不存在");
        }
        //本地内存标记，减少redis的访问，单机版的秒杀可以这么玩，分布式环境下就别这么玩了
        boolean temp= (boolean) map.get(productId);
        if (temp){
            throw  new CheckException("商品抢购完毕~！");
        }
        //查看是否重复秒杀了
        SeckillOrder repeatSeckill = ordersService.isRepeatSeckill(users.getUser_id(), productId);
        if (repeatSeckill!=null){
            throw  new CheckException("请不要重复秒杀");
        }
        //访问redis，并将商品的值减1
        long number =redisService.decr(Constants.SECKILL_PRODECT+":"+productId);
        //判断redis内商品数量，抢购完毕改变一个本地内存标记
        if (number<0){
            map.put(productId,true);
            throw  new CheckException("商品被抢完了~！！！！");
        }

        SeckillMessage seckillMessage = new SeckillMessage();
        seckillMessage.setProduct_id(productId);
        seckillMessage.setUsers(users);
        //将信息交给Rabbit队列处理订单，rabbitmq异步处理下单流程
        mqSender.sendMiaoshaMessage(JsonUtils.ObjectTojson(seckillMessage));
        return "OK";
    }
    @Override
    public String reSetProduct() {
        log.info("开始");

        List<Product> allSeckillPrroduct = productService.findAllSeckillPrroduct();
        System.out.println(allSeckillPrroduct);
        if (allSeckillPrroduct.isEmpty()){
            log.info("插入");
            Product product =new Product();
            product.setNumber(10);
            product.setDescription("就是那么帅");
            product.setProduct_name("Gentle");
            for (int i=1;i<3;i++){
                product.setProduct_id((long)i);
                productService.insertProductInfo(product);
            }
            return reSetProduct();
        }

        //插入500个用户（用得是set插入，不建议，建议用hash存储，这里懒得改了）
        redisService.pipSet();
        log.info("查询");
        for (Product product :allSeckillPrroduct){
            productService.updateProductInfo(product);
            //秒杀的商品的id
            redisService.set(Constants.SECKILL_PRODECT+":"+product.getProduct_id(),product.getNumber());
            //放入map，本地内存抗压
            map.put(product.getProduct_id(),false);
        }
        return "OK";
    }

}
