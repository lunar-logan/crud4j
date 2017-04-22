package com.crud4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dell on 22-04-2017.
 */
public class SQLTypeToJavaTypeMapping {
    private static final Class<Object> DEFAULT_TYPE = Object.class;
    private static final Map<String, Class> sqlTypeToJavaTypeMap = new HashMap<>();

    static {
        sqlTypeToJavaTypeMap.put("int", Integer.class);
        sqlTypeToJavaTypeMap.put("long", Long.class);
        sqlTypeToJavaTypeMap.put("varchar", String.class);
        sqlTypeToJavaTypeMap.put("date", Date.class);
        sqlTypeToJavaTypeMap.put("timestamp", Date.class);
    }

    public static Class getInputType(String sqlType) {
        if (sqlTypeToJavaTypeMap.containsKey(sqlType)) {
            return sqlTypeToJavaTypeMap.get(sqlType);
        }
        return DEFAULT_TYPE;
    }
}
