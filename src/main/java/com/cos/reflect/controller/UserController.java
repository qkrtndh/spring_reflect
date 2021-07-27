package com.cos.reflect.controller;

import com.cos.reflect.anno.RequestMapping;
import com.cos.reflect.controller.dto.JoinDto;
import com.cos.reflect.controller.dto.LoginDto;

public class UserController {
	
	@RequestMapping("/user/join")
	public String join(JoinDto dto) {
		System.out.println("join함수 호출");
		return "/";
	}

	@RequestMapping("/user/login")
	public String login(LoginDto dto) {
		System.out.println("login함수 호출");
		return "/";
	}

	@RequestMapping("/user")
	public String user() {
		System.out.println("user함수 호출");
		return "/";
	}
	
	@RequestMapping("/user/hello")
	public String hello() {
		System.out.println("hello함수 호출");
		return "/"; 
	}
}
