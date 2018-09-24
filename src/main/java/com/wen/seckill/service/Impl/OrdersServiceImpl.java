package com.wen.seckill.service.Impl;

import com.wen.seckill.constant.Constants;
import com.wen.seckill.domian.SeckillOrder;
import com.wen.seckill.exception.CheckException;
import com.wen.seckill.factory.OrdersFactory;
import com.wen.seckill.mapper.OrdersMapper;
import com.wen.seckill.redis.RedisService;
import com.wen.seckill.service.OrdersService;
import com.wen.seckill.utils.JsonUtils;
import com.wen.seckill.utils.Sequence;
import com.wen.seckill.vo.OrderInfo;
import com.wen.seckill.vo.Product;
import com.wen.seckill.vo.Users;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import sun.applet.Main;

/**
 * @Description: 订单操作
 * @Author: Gentle
 * @date 2018/9/17  13:55
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    RedisService redisService;

    @Autowired
    OrdersMapper ordersMapper;

    /**
     * 判断是否重复下单
     * @param userId
     * @param productId
     * @return
     */
    @Override
    public SeckillOrder isRepeatSeckill(int userId, long productId) {

        String value = redisService.get(Constants.USER_PRODUCT_BY_SECKILL + ":" + userId + "_" + productId);
        if (value != null) {
            return JsonUtils.jsonToObject(value, SeckillOrder.class);
        }
        return null;
    }

    /**
     * 减少库存数量
     * @param productId
     * @param version
     * @return
     */
    @Override
    public boolean reduceProductNumber(long productId, int version) {
        int temp = ordersMapper.reduceProductNumber(productId, version);
        if (temp != 1) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public SeckillOrder doSeckill(Product product, Users users) {

        System.out.println(product +"    "+users);
       boolean flag= reduceProductNumber(product.getProduct_id(),product.getVersion());
        if (flag){
            //获取单例对象生成订单。
            Sequence sequence =OrdersFactory.getInstance();
            SeckillOrder seckillOrder = new SeckillOrder();
            seckillOrder.setProduct_id(product.getProduct_id());
            seckillOrder.setOrder_id(sequence.nextId());
            seckillOrder.setUser_id(users.getUser_id());
            //插入数据库
            insertOrder(seckillOrder);
            //存入redis
           String mess=  JsonUtils.ObjectTojson(seckillOrder);
            //放入redis，用于判断是否重复下单
            redisService.set(Constants.USER_PRODUCT_BY_SECKILL + ":" + users.getUser_id() + "_" + product.getProduct_id(),mess);
            return seckillOrder;
        }else {
            throw  new CheckException("商品售完了~");
        }
    }

    /**
     * 抢购订单入库
     * @param seckillOrder
     * @return
     */
    @Override
    public int insertOrder(SeckillOrder seckillOrder) {
        return ordersMapper.insertOrder(seckillOrder);
    }


}
