package com.wen.seckill.exception;



/**
 * 自定义异常，主要用于校验错误异常
 * @author Gentle
 *
 */
public class CheckException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public CheckException() {
   }

   public CheckException(String message) {
      super(message);
   }

   public CheckException(Throwable cause) {
      super(cause);
   }

   public CheckException(String message, Throwable cause) {
      super(message, cause);
   }



}