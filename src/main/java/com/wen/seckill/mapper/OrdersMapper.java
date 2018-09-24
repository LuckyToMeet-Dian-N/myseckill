package com.wen.seckill.mapper;

import com.wen.seckill.domian.SeckillOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/9/19  16:33
 */
@Mapper
public interface OrdersMapper {
    /**
     * 减库存，附带版本号
     * @param productId
     * @param version
     * @return
     */
    @Update("update product set number=number-1,version=version+1 where version=#{version} and product_id=#{productId}")
    int reduceProductNumber(@Param("productId") long productId, @Param("version") int version);

    /**
     * 插入订单
     * @param seckillOrder
     * @return
     */
    @Insert("insert into orders value(#{order_id},#{product_id},#{user_id},now(),0)")
    int insertOrder(SeckillOrder seckillOrder);

}
