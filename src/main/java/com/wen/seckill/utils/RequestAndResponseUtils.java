package com.wen.seckill.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 请求的工具类工具类
 * @author Gentle
 *
 */

public class RequestAndResponseUtils {
	/**
	 * 获取request对象
	 * @return
	 */

	public static HttpServletRequest getRequest() {
		// HttpServlerRequest
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

	}
	/**
	 * 获取responnse对象
	 * @return
	 */
	
	public  static HttpServletResponse getResponse() {
		// HttpServlerRequest
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

	}
	
	
}