package com.wen.seckill.mapper;

import com.wen.seckill.vo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/9/19  16:01
 */
@Mapper
public interface ProductMapper {
    /**
     * 获取商品信息
     * @param productId
     * @return
     */
    @Select(" select * from product where product_id=#{productId}")
    Product getProductInfo(@Param("productId") long productId);

    /**
     * 查询所有秒杀商品
     * @return
     */
    @Select("select * from product ")
    List<Product> findAllSeckillPrroduct();
    @Update("update product set number=#{number} where product_id=#{product_id} ")
    int updateProductInfo(Product product);

    /**
     * 插入商品信息
     * @param product
     * @return
     */
    @Insert("insert into product value(#{product_id},#{product_name},#{number},#{description},0)")
    int insertProductInfo(Product product);

}
