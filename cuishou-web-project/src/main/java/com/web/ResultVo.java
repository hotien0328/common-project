package com.web;

import com.vo.BaseVo;



/**
 * 请求处理结果对象，ajax处理等
 * 
 * 
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright guang.com(c) 2014
 * 
 * @author yes
 * @created Jul 4, 2014 3:25:13 PM
 * @version $Id: ResultVo.java 89719 2015-06-24 10:46:44Z zhjy $
 */
public class ResultVo<T> extends BaseVo {
	private static final long serialVersionUID = -1222614520893986846L;
	
	private T result;
	
	/**
	 * 请求处理结果
	 */
	private boolean isSuccess = false;
	
	/**
	 * 请求回传code
	 */
	private String code;
	
	/**
	 * 请求处理结果描述
	 */
	private String resultDes;
	
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getResultDes() {
		return resultDes;
	}

	public void setResultDes(String resultDes) {
		this.resultDes = resultDes;
	}
}
