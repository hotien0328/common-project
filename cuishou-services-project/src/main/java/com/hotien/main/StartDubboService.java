package com.hotien.main;

import java.io.IOException;

import org.junit.Test;

import com.util.ServiceLocator;

public class StartDubboService
{
    @Test
    public void test() throws InterruptedException 
    {
        System.out.println("dubbo开始启动");
        ServiceLocator.start();
        System.out.println("start...");

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
