package com.chocod.summarize.proxy;

public class AuthServiceImpl implements IAuthService {

	@Override
	public boolean accountCorrect(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		return true;
	}

}
