package com.cos.reflect.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.reflect.controller.UserController;
//분기시키기
public class Dispatcher implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("디스패처 진입");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//System.out.println("컨텍스트 패스: "+request.getContextPath());
		//System.out.println("식별자 주소: "+request.getRequestURI());
		//System.out.println("전체 주소: "+request.getRequestURL());
		
		//http://localhost:8080/reflect/user 접속시 user 파싱
		String endPoint = request.getRequestURI().replaceAll(request.getContextPath(),"");
		System.out.println("endPoint: "+endPoint);
		
		UserController userController = new UserController();
		if(endPoint.equals("/join")) {
			userController.join();
		}
		else if(endPoint.equals("/login")) {
			userController.login();
		}
		else if(endPoint.equals("/user")) {
			userController.user();
		}
	}

}
