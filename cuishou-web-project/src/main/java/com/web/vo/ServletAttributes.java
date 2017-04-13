package com.web.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * 
 * Title: <h3>获取request/response工具</h3>
 * 
 * Description:
 * 
 * Copyright: guang.com(c) 2014
 * 
 * @author zhenghr
 * @created 上午9:30:53
 * @version $Id: ServletAttributes.java 89112 2015-06-13 08:45:20Z zhjy $
 */
public class ServletAttributes {

	private static ThreadLocal<HttpServletRequest> requestContext = new ThreadLocal<HttpServletRequest>();

	private static ThreadLocal<HttpServletResponse> responseContext = new ThreadLocal<HttpServletResponse>();

	public static void setResponse(HttpServletResponse response) {
		responseContext.set(response);
	}

	public static void setRequest(HttpServletRequest request) {
		requestContext.set(request);
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = requestContext.get();
		if (null == request) { // 如果拦截器没有成功保存request，则通过另一种方式取request
			request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
		}
		return request;
	}

	public static HttpServletResponse getResponse() {
		return responseContext.get();
	}

}
