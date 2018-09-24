package com.wen.seckill.controller.publics;

import com.wen.seckill.result.ResultBean;
import com.wen.seckill.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 开放的api接口，不需要登录获取权限即可访问
 * @Author: Gentle
 * @date 2018/9/20  19:27
 */
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @RequestMapping(value = "login")
    public ResultBean<String> userLogin(@RequestParam("account") String account, @RequestParam("password") String password){

        return new ResultBean<>(loginService.selectUser( account, password));
    }


}
