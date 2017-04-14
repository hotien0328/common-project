package com.web.vo;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



import com.vo.Config;
import com.web.util.RequestUtil;

/**
 * 
 * 
 * Title:
 * <h1>鍙傛暟鎷︽埅</h1> Description:
 * <p>
 * <ul>
 * <ol>
 * 淇濆瓨褰撳墠绾跨▼涓殑request涓巖esponse <b>鐢ㄤ笌涔嬪悗鑾峰彇</b>
 * </ol>
 * <ol>
 * 灏哢RI涓殑鍙傛暟鏀捐嚦model涓� <b>鐢ㄤ笌freemarker涓洿鎺ヨ幏鍙�</b>
 * </ol>
 * </ul>
 * </p>
 * Copyright: guang.com(c) 2014
 * 
 * @author zhenghr
 * @created 涓婂崍9:34:33
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
