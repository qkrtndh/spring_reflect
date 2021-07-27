package com.cos.reflect.filter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.reflect.anno.RequestMapping;
import com.cos.reflect.controller.UserController;

//분기시키기
public class Dispatcher implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// System.out.println("디스패처 진입");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// System.out.println("컨텍스트 패스: "+request.getContextPath());
		// System.out.println("식별자 주소: "+request.getRequestURI());
		// System.out.println("전체 주소: "+request.getRequestURL());

		// http://localhost:8080/reflect/user 접속시 user 파싱
		String endPoint = request.getRequestURI().replaceAll(request.getContextPath(), "");
		// System.out.println("endPoint: "+endPoint);

		UserController userController = new UserController();
		// userController 의 선언된 메소드들을 가져온다.
		Method[] methods = userController.getClass().getDeclaredMethods();

//		//리플렉션 -> 메소드를 런타임(실행)시점에서 찾아내서 실행
//		for (Method method : methods) {
//			//System.out.println(method.getName());
//			if (endPoint.equals("/" + method.getName())) {
//				try {
//					method.invoke(userController);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
		for (Method method : methods) {// 4바퀴 join,login,user,hello
			Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
			RequestMapping requestMapping = (RequestMapping) annotation;
			System.out.println(requestMapping.value());
			if (requestMapping.value().equals(endPoint)) {
				try {
					String path = (String)method.invoke(userController);
					RequestDispatcher dis = request.getRequestDispatcher(path);
					dis.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

}
