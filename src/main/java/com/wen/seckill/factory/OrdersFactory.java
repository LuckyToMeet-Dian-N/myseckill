package com.wen.seckill.factory;

import com.wen.seckill.utils.Sequence;

/**
 * @Description: 专门生产订单号,最好的单例模式，不会的请自行百度
 * @Author: Gentle
 * @date 2018/9/19  16:46
 */
public class OrdersFactory {

    private OrdersFactory() { }

    private static class ProduceOrder {
        private static final Sequence sequence = new Sequence(1, 1);

    }

    public static final Sequence getInstance() {
        return ProduceOrder.sequence;
    }


}
