package com.chocod.summarize.dubbo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProviderMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:dubbo-provider.xml"});
        context.start();

        System.in.read(); // 按任意键退出
		
	}

}
