package com.vo;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.common.collect.Lists;

import net.sf.cglib.beans.BeanCopier;

/**
 * 
 * 
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2008
 * 
 * @author haoxz11
 * @created Mar 7, 2008 11:56:03 AM
 * @version $Id: BaseVo.java 89112 2015-06-13 08:45:20Z zhjy $
 */
public class BaseVo implements Serializable {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	transient private static final long serialVersionUID = 1L;
	private static ConcurrentMap<String, BeanCopier> beanCopiers = new ConcurrentHashMap<String, BeanCopier>();

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}

	public String toLineString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE).toString();
	}

	/**
	 * 把自身属性拷贝到其他变量中,一般比正常拷贝慢20倍左右，不过比对象映射快10倍，尽量不使用。
	 * 
	 * @param clz
	 * @return
	 */
	public <T> T copyTo(Class<T> clz) {
		try {
			T clone = clz.newInstance();
			String key = new StringBuilder().append(clz.getName()).append(":").append(this.getClass().getName())
					.toString();
			BeanCopier copier = null;
			if (beanCopiers.containsKey(key)) {
				copier = beanCopiers.get(key);
			} else {
				copier = BeanCopier.create(this.getClass(), clz, false);
				beanCopiers.putIfAbsent(key, copier);
			}
			copier.copy(this, clone, null);
			return clone;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 拷贝一个列表
	 * 
	 * @param sources
	 * @param clz
	 * @return
	 */
	public static <A extends BaseVo, B extends BaseVo> List<B> copyListTo(List<A> sources, Class<B> clz) {
		try {
			if (sources == null) {
				return null;
			}
			List<B> newList = Lists.newArrayList();
			if (sources.size() == 0) {
				return newList;
			}
			A val = sources.get(0);
			String key = new StringBuilder().append(clz.getName()).append(":").append(val.getClass().getName())
					.toString();
			BeanCopier copier = null;
			if (beanCopiers.containsKey(key)) {
				copier = beanCopiers.get(key);
			} else {
				copier = BeanCopier.create(val.getClass(), clz, false);
				beanCopiers.putIfAbsent(key, copier);
			}
			for (A a : sources) {
				B clone = clz.newInstance();
				copier.copy(a, clone, null);
				newList.add(clone);
			}
			return newList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
