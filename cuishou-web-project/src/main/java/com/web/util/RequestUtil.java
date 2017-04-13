package com.web.util;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import aicai.util.DateUtil;
import aicai.util.NumberUtil;
import aicai.util.SecurityUtil;
import aicai.util.StringUtil;
import aicai.vo.BaseVo;
import aicai.vo.search.SearchQuery;

/**
 * 
 * 
 * Title:
 * <h1>request工具类</h1> Description:
 * 
 * Copyright: guang.com(c) 2014
 * 
 * @author zhenghr
 * @created 上午9:34:07
 * @version $Id: RequestUtil.java 100126 2015-08-14 01:47:27Z zhjy $
 */
public class RequestUtil {
	private static final String[] MOBILE_SPECIFIC_SUBSTRING = { "iPhone", "Android", "Windows Phone", "HTC", "Nokia",
			"UCBrowser" // IOS,
						// android
			/*
			 * "MIDP","Opera Mobi", "Opera Mini","BlackBerry","HP iPAQ"
			 * ,"IEMobile", "MSIEMobile","LG", "MOT","Symbian","Fennec",
			 * "Maemo","Tear","Midori","armv", "Windows CE"
			 * ,"WindowsCE","Smartphone","240x320",
			 * "176x220","320x320","160x160","webOS",
			 * "Palm","Sagem","Samsung","SGH",
			 * "SIE","SonyEricsson","MMP","UCWEB"
			 */
	};

	public static final String RETURN_JSON_HADE = "RETURN_JSON_HADE";

	private static final String[] IPAD = { "iPad" };

	private static final String[] UC_BROWSER = { "UCBrowser", "U2/1.0.0" };

	public static final String CSRF_TOKEN = "csrf_token_value";

	/**
	 * 是否来自guang.b5m.com
	 * 
	 * @return
	 */
	public static boolean isB5m(HttpServletRequest request) {
		if (request != null) {
			String serverName = request.getServerName();
			if (StringUtils.isNotBlank(serverName) && serverName.contains("b5m")) {
				return true;
			}
		}
		return false;
	}

	public static String getRealIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String[] arr = ip.split(",");
		for (String i : arr) {
			ip = i.trim();
		}
		if (ip.length() > 15) {
			ip = StringUtils.substring(ip, 0, 15);
		}
		return ip;
	}

	public static boolean isMobile(HttpServletRequest request) {
		return checkMobile(request, MOBILE_SPECIFIC_SUBSTRING);
	}

	public static boolean isIpad(HttpServletRequest request) {
		return checkMobile(request, IPAD);
	}

	public static boolean checkUCJisu(HttpServletRequest request) {
		return checkMobile(request, UC_BROWSER);
	}

	/**
	 * 
	 * 是否来自WEB外的平台
	 * 
	 * @param request
	 * @return
	 * @since Guang 1.0-SNAPSHOT
	 */
	private static boolean checkMobile(HttpServletRequest request, String[] specific) {
		String userAgent = request.getHeader("user-agent");
		if (StringUtils.isNotBlank(userAgent)) {
			for (String mobile : specific) {
				if (userAgent.contains(mobile) || userAgent.contains(mobile.toUpperCase())
						|| userAgent.contains(mobile.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) || hasReturnJson(request);
	}

	public static boolean hasReturnJson(HttpServletRequest request) {
		return "y".equals(request.getAttribute(RETURN_JSON_HADE));
	}

	public static void setToAjax(HttpServletRequest request) {
		request.setAttribute(RETURN_JSON_HADE, "y");
	}

	public static boolean isRobot(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		if (StringUtils.isNotBlank(userAgent)) {
			if (userAgent.toLowerCase().contains("spider") || userAgent.toLowerCase().contains("bot")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public static void send301(HttpServletResponse response, String redirectUrl) {
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		response.setHeader("Location", redirectUrl);
		response.setHeader("Connection", "close");
	}

	public static void send302(HttpServletResponse response, String redirectUrl) {
		response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", redirectUrl);
		response.setHeader("Connection", "close");
	}

	public static String getReferer(HttpServletRequest request) {
		return request.getHeader("Referer");
	}

	public static void removeSessionAttribute(HttpServletRequest request, String name) {
		request.getSession().removeAttribute(name);
	}

	/**
	 * 当前URI
	 * 
	 * @return
	 */
	public static String getURI(HttpServletRequest request) {
		return request.getRequestURI();
	}

	/**
	 * 得到当前Request的所有Post入参
	 * 
	 * @param request
	 * @return
	 */
	public static String getPostString(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		StringBuilder content = new StringBuilder();
		for (Entry<String, String[]> kv : map.entrySet()) {
			content.append(kv.getKey()).append(":");
			for (String v : kv.getValue()) {
				content.append(v).append(",");
			}
			content.append(";");
		}
		if (content.length() == 0) {
			content.append(request.getAttribute("content"));
		}
		return content.toString();
	}

	/**
	 * 得到当前URL
	 * 
	 * @return
	 */
	public static String getURL(HttpServletRequest request) {
		String query = request.getQueryString();
		String tmp = "";
		if (StringUtils.isBlank(query)) {
			tmp = request.getRequestURI();
		} else {
			tmp = request.getRequestURI() + "?" + request.getQueryString();
		}
		return tmp;
	}

	/**
	 * 得到表单中的参数
	 * 
	 * @param response
	 *            The String
	 */
	public static String getParameter(HttpServletRequest request, String key) {
		return request.getParameter(key);
	}

	/**
	 * 得到表单的int值，么有返回默认值
	 * 
	 * @param key
	 * @param defaultVal
	 * @return
	 */
	public static int getIntParameter(HttpServletRequest request, String key, int defaultVal) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(getParameter(request, key))) {
			return defaultVal;
		}
		return NumberUtil.parseInt((String) getParameter(request, key));
	}

	/**
	 * 得到表单中的参数
	 * 
	 * @param key
	 * @return
	 */
	public static long getLongParameter(HttpServletRequest request, String key, long defaultVal) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(getParameter(request, key))) {
			return defaultVal;
		}
		return NumberUtil.parseLong((String) getParameter(request, key));
	}

	/**
	 * 得到加密过的表单中的参数
	 * 
	 * @param response
	 *            The String
	 */
	public static String getSParameter(HttpServletRequest request, String key) {
		String value = StringUtils.trim(SecurityUtil.decrypt(getParameter(request, key)));
		return value;
	}

	/**
	 * 得到加密过的表单中的参数
	 * 
	 * @param response
	 *            The String
	 */
	public static int getSIntParameter(HttpServletRequest request, String key) {

		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(getParameter(request, key))) {
			return -1;
		}
		return NumberUtil.parseInt((String) getSParameter(request, key));
	}

	/**
	 * 得到加密过的表单中的参数
	 * 
	 * @param response
	 *            The String
	 */
	public static long getSLongParameter(HttpServletRequest request, String key) {

		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(getParameter(request, key))) {
			return -1;
		}
		return NumberUtil.parseLong((String) getSParameter(request, key));
	}

	/**
	 * 得到表单中的参数
	 * 
	 * @param response
	 *            The String[]
	 */
	public static String[] getParameterValues(HttpServletRequest request, String key) {
		return request.getParameterValues(key);
	}

	/**
	 * 得到表单中的参数
	 * 
	 * @param response
	 *            The String[]
	 */
	public static Long[] getLongParameterValues(HttpServletRequest request, String key) {
		String[] strings = request.getParameterValues(key);
		if (strings != null && strings.length > 0) {
			int l = strings.length;
			Long[] ints = new Long[l];
			for (int i = 0; i < l; i++) {
				ints[i] = NumberUtil.parseLong(strings[i]);
			}
			return ints;
		} else {
			return null;
		}
	}

	/**
	 * 得到表单中的参数
	 * 
	 * @param response
	 *            The String[]
	 */
	public static int[] getIntParameterValues(HttpServletRequest request, String key) {
		String[] strings = request.getParameterValues(key);
		if (strings != null && strings.length > 0) {
			int l = strings.length;
			int[] ints = new int[l];
			for (int i = 0; i < l; i++) {
				ints[i] = NumberUtil.parseInt(strings[i]);
			}
			return ints;
		} else {
			return null;
		}
	}

	/**
	 * 得到表单中的参数
	 * 
	 * @param response
	 *            The String[]
	 */
	public static int[] getSIntParameterValues(HttpServletRequest request, String key) {
		String[] ints = getParameterValues(request, key);
		int[] sints = null;
		if (ints != null && ints.length > 0) {
			sints = new int[ints.length];
			for (int i = 0; i < ints.length; i++) {
				if (!StringUtils.isEmpty(ints[i])) {
					sints[i] = Integer.parseInt(SecurityUtil.decrypt(ints[i]));
				}
			}
		}
		return sints;
	}

	/**
	 * 得到表单中的参数
	 * 
	 * @param response
	 *            The String[]
	 */
	public static Integer[] getIntegerParameterValues(HttpServletRequest request, String key) {
		int[] ints = getIntParameterValues(request, key);
		Integer[] integers = null;
		if (ints != null && ints.length > 0) {
			integers = new Integer[ints.length];
			for (int i = 0; i < ints.length; i++) {
				integers[i] = ints[i];
			}
		}
		return integers;
	}

	/**
	 * 得到表单中的参数
	 * 
	 * @param response
	 *            The String
	 */
	public static double getDoubleParameter(HttpServletRequest request, String key) {

		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(getParameter(request, key))) {
			return -1;
		}
		return NumberUtil.parseDouble((String) getParameter(request, key));
	}

	public static Date getDateParameter(HttpServletRequest request, String args) {
		return getDateParameter(request, args, "yyyy-MM-dd");
	}

	public static Date getDateParameter(HttpServletRequest request, String args, String format) {
		Date date = null;
		String sdate = getParameter(request, args);
		if (!StringUtils.isEmpty(sdate)) {
			date = DateUtil.getDateDf(sdate, format);
		}
		return date;
	}

	/**
	 * 设置VO中的参数全部写到Model中作为参数
	 * 
	 * @param model
	 * @param vo
	 */
	public static void putVo2Model(Model model, BaseVo vo) {
		Method[] ms = vo.getClass().getMethods();
		for (Method m : ms) {
			if (m.getName().startsWith("get")) {
				String name = StringUtil.findStr(m.getName(), "get", null);
				if (StringUtil.isNotEmpty(name) && !"Class".equals(name)) {
					String first = String.valueOf(name.charAt(0));
					name = name.replaceFirst(first, first.toLowerCase());
					try {
						Object o = m.invoke(vo);
						if (o != null) {
							model.addAttribute(name, o);
						}
					} catch (Exception e) {

					}
				}
			}
		}

	}

	public static void main(String[] args) {
		Model model = new ExtendedModelMap();
		SearchQuery vo = SearchQuery.newQuery(0, 100);
		vo.putQuery("ab", "bc");
		RequestUtil.putVo2Model(model, vo);
		System.out.println(model);
	}
}
