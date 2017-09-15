package com.chocod.summarize.proxy;

public class TestProxy {

	
	public static void main(String[] args) {
	
		AuthServiceComsumerProxy proxy = new AuthServiceComsumerProxy();
//		Object obj = proxy.bindImpl(new AuthServiceImpl());
		Object obj = proxy.bindJustInterface(IAuthService.class);
		IAuthService impl = (IAuthService)obj;
		boolean result = impl.accountCorrect("zhangsan", "123456");
		System.out.println("result:"+result);
	}
	
}
