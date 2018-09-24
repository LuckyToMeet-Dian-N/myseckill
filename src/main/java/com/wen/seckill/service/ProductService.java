package com.wen.seckill.service;

import com.wen.seckill.vo.Product;

import java.util.List;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/9/19  15:44
 */
public interface ProductService {

    /**
     * 根据商品id查询商品信息
     * @param productId
     * @return
     */
    Product getProductInfo(long productId);

    /**
     * 查询所有秒杀商品，用于初始化秒杀的商品
     * @return
     */
    List<Product> findAllSeckillPrroduct();

    int updateProductInfo(Product product);

    int insertProductInfo(Product product);

}
