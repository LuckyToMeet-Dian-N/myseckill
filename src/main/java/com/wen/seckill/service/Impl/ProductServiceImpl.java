package com.wen.seckill.service.Impl;

import com.wen.seckill.mapper.ProductMapper;
import com.wen.seckill.service.ProductService;
import com.wen.seckill.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/9/19  15:45
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;


    @Override
    public Product getProductInfo(long productId) {
        return productMapper.getProductInfo(productId);
    }

    @Override
    public List<Product> findAllSeckillPrroduct() {
        return productMapper.findAllSeckillPrroduct();
    }

    @Override
    public int updateProductInfo(Product product) {
        return productMapper.updateProductInfo(product);
    }

    @Override
    public int insertProductInfo(Product product) {
        return productMapper.insertProductInfo(product);
    }
}
