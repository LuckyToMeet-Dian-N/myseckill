package com.wen.seckill.handler;



import com.wen.seckill.exception.CheckException;
import com.wen.seckill.exception.UnloginException;
import com.wen.seckill.result.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理器
 * @author Gentle
 */
@ControllerAdvice
@Slf4j
public class HandlerException {
    /**
     * 处理未知异常
     *
     * @param e
     * @return
     */

//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ResultBean unknowException(Exception e) {
//        log.error("系统出现未知异常", e);
//        ResultBean resultBean = new ResultBean();
//        resultBean.setCode(ResultBean.UNKNOWN_EXCEPTION);
//        resultBean.setMsg("系统出现未知异常，请于管理员联系");
//        /**
//         * 未知异常的话，这里写逻辑，发邮件，发短信都可以、、
//         */
//        return resultBean;
//    }

    /**
     * 处理校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = CheckException.class)
    @ResponseBody
    public ResultBean handlerCheckException(CheckException e) {
        log.error("发生了已知错误："+e.getMessage());
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(ResultBean.CHECK_FAIL);
        resultBean.setMsg(e.getMessage());
        return resultBean;
    }


    /**
     * 处理没有登录异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnloginException.class)
    @ResponseBody
    public ResultBean handlerUnloginException(UnloginException e) {
        log.error("发生了已知错误："+e.getMessage());
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(ResultBean.NO_LOGIN);
        resultBean.setMsg(e.getMessage());
        return resultBean;
    }





}