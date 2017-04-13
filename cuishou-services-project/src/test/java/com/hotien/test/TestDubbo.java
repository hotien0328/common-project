package com.hotien.test;

import java.io.IOException;

import org.junit.Test;

import com.util.ServiceLocator;

public class TestDubbo {
	
	@Test
	public void test() throws InterruptedException {
		// TODO Auto-generated method stub
		ServiceLocator.start();
		System.out.println("start...");

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
