package com.wen.seckill.controller;

import com.wen.seckill.result.ResultBean;
import com.wen.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/9/18  16:06
 */
@RestController
@Slf4j
public class SeckillController {

    @Autowired
    SeckillService seckillService;

    /**
     * 秒杀，只允许传一个商品号来，就不做各种限制操作
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "seckill")
    public ResultBean<String> doSeckill(@RequestParam("productId") long productId) {
        long a = System.currentTimeMillis();
        ResultBean resultBean = new ResultBean<>(seckillService.doSeckill(productId));
        log.info("花费时间：" + (System.currentTimeMillis() - a));
        return resultBean;
    }

    /**
     * 初始化商品。初始化秒杀的商品。默认商品的id 是  1  和  2
     * @return
     */
    @RequestMapping(value = "resetProduct")
    public ResultBean<String> reset() {
        return new ResultBean<>(seckillService.reSetProduct());
    }

}
