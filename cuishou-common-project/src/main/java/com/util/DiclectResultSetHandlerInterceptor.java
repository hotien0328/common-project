package com.util;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

/**
 * 
 * MyBatis插件，捕捉ResultSetHandler
 * 
 * Title:
 * 
 * Description:
 * 
 * Copyright: (c) 2011
 * 
 * @author haoxz11
 * @created 2011-4-18 上午12:11:30
 * @version $Id: DiclectResultSetHandlerInterceptor.java 89112 2015-06-13 08:45:20Z zhjy $
 */
@Intercepts( { @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class DiclectResultSetHandlerInterceptor implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable {
		DefaultResultSetHandler resultSet = (DefaultResultSetHandler) invocation
				.getTarget();

		RowBounds rowBounds = (RowBounds) ReflectUtil.getFieldValue(resultSet,
				"rowBounds");

		if (rowBounds.getLimit() > 0
				&& rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT) {
			ReflectUtil.setFieldValue(resultSet, "rowBounds", new RowBounds());
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}