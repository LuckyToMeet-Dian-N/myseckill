package com.wen.seckill.vo;

import lombok.Data;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/9/19  15:45
 */
@Data
public class Product {

    private Long product_id;
    private String product_name;
    private Integer number;
    private String description;
    private Integer version;

}
