package com.util;

import com.vo.Dialect;

/**
 * 
 * 
 * 
 * Title:
 * 
 * Description:
 * 
 * Copyright: (c) 2011
 * 
 * @author haoxz11
 * @created 2011-4-17 下午03:51:10
 * @version $Id: MySQLDialect.java 89112 2015-06-13 08:45:20Z zhjy $
 */
public class MySQLDialect extends Dialect {
    public boolean supportsLimitOffset() {
        return true;
    }

    public boolean supportsLimit() {
        return true;
    }

    public String getLimitString(String sql, int offset,
            String offsetPlaceholder, int limit, String limitPlaceholder) {
        if (offset >= 0){
            return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
        }   

        return sql + " limit " + limitPlaceholder;
    }
}