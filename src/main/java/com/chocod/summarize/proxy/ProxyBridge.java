package com.chocod.summarize.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * 模拟网络通信,sendRequest()、getRequest()之间应当是某个远程通信过程，比如http、mina、netty
 * @author dingzhiyong
 *
 */
public class ProxyBridge {

	/**
	 * 网络通信发送端，省略了序列化步骤
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object sendRequest(String methodName, Class[] paramterTypes , Object[] args){
		try {
			return ProxyBridge.getRequest(methodName,paramterTypes, args);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 网络通信的接收端，省略了序列化步骤
	 * @param method
	 * @param args
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static Object getRequest(String methodName, Class[] paramterTypes , Object[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException {
		Method method = Class.forName("com.chocod.summarize.proxy.IAuthService").getMethod(methodName, paramterTypes);
		
		AuthServiceProducerProxy proxy = new AuthServiceProducerProxy();
		Object proxyObj = proxy.bindImpl(new AuthServiceImpl());
		try {
//			return proxy.invoke(proxyObj, method, args);
			return method.invoke(proxyObj, args);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		try {
			return method.invoke(new AuthServiceImpl(), args);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		return null;
	}
}
