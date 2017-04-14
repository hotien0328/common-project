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
     * 这里是要额外关闭的服务
     */
    public static void closeService() {
        
    }

}
