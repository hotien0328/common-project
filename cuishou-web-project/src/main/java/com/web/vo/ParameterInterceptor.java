package com.web.vo;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import aicai.config.Config;

import com.web.util.RequestUtil;

/**
 * 
 * 
 * Title:
 * <h1>参数拦截</h1> Description:
 * <p>
 * <ul>
 * <ol>
 * 保存当前线程中的request与response <b>用与之后获取</b>
 * </ol>
 * <ol>
 * 将URI中的参数放至model中 <b>用与freemarker中直接获取</b>
 * </ol>
 * </ul>
 * </p>
 * Copyright: guang.com(c) 2014
 * 
 * @author zhenghr
 * @created 上午9:34:33
 * @version $Id: ParameterInterceptor.java 89112 2015-06-13 08:45:20Z zhjy $
 */
public class ParameterInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ServletAttributes.setRequest(request);
		ServletAttributes.setResponse(response);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			// vm path replace
			String viewName = modelAndView.getViewName();
			// set parameters to model
			if (!RequestUtil.isAjax(request)) {

				if (null != viewName && !viewName.startsWith("redirect") && !viewName.startsWith("forward")) {

					modelAndView.addObject("domain", Config.getProjectHost());
					modelAndView.addObject("projectUrl", Config.getProjectUrl());
					if (!modelAndView.getModel().containsKey("thisUrl")) {
						modelAndView.addObject("thisUrl", RequestUtil.getURI(request));
					}

					Enumeration<String> parameterNames = request.getParameterNames();
					while (parameterNames.hasMoreElements()) {
						String paramName = parameterNames.nextElement();
						if (!modelAndView.getModel().containsKey(paramName)) {
							modelAndView.addObject(paramName, request.getParameter(paramName));
						}
					}
				}
			}
		}
		super.postHandle(request, response, handler, modelAndView);
	}

}
