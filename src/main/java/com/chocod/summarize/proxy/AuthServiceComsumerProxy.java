package com.chocod.summarize.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AuthServiceComsumerProxy implements InvocationHandler {

	
	private Object originObj;
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public Object bindImpl(Object obj){
		this.originObj = obj;
		Object proxyObj = Proxy.newProxyInstance(originObj.getClass().getClassLoader(),  
				originObj.getClass().getInterfaces(), this);
		return proxyObj;
	}
	
	public Object bindJustInterface(Class interfaceClass){
		return Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[] {interfaceClass}, this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		Object result;
		result = ProxyBridge.sendRequest(method.getName(), method.getParameterTypes(), args);	
		return result;
	}

	
}
