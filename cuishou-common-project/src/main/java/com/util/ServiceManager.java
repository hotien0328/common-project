package com.util;

public class ServiceManager
{
    public static <T> T getService(Class<T> clazz) {
        return ServiceLocator.getService(clazz);
    }
    public static <T> T getService(String name, Class<T> clazz) {
        return ServiceLocator.getService(name, clazz);
    }
    
    /**
     * ������Ҫ����رյķ���
     */
    public static void closeService() {
        
    }

}
