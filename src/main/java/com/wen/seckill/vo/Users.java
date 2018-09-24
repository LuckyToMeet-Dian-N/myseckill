package com.wen.seckill.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 用户对象，这里偷懒和数据库字段名一致（正常开发时尽量不要一致）
 * @Author: Gentle
 * @date 2018/9/17 0017 10:17
 */
@Data
public class Users {

    private Integer user_id;
    private String user_account;
    private String password;
    private String user_name;
    private Date birthday;

}
