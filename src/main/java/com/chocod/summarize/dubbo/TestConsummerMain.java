package com.chocod.summarize.dubbo;

import java.io.IOException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chocod.summarize.proxy.IAuthService;

public class TestConsummerMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:dubbo-consummer.xml"});
		context.start();

        IAuthService authService = (IAuthService)context.getBean("authService"); // 获取远程服务代理
        boolean result = authService.accountCorrect("zhangsan", "123456"); // 执行远程方法

        System.out.println("result:"+result);
		
        
        /**
         
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("file:E:/home/webdata/zhaoshangbackend/webroot/config/applicationContext.xml");
		MerchantDao md = (MerchantDao)ctx.getBean("MerchantDao");
         
         */
	}

}
