package com.cos.reflect.controller;

import com.cos.reflect.anno.RequestMapping;

public class UserController {
	
	@RequestMapping
	public void join() {
		System.out.println("join함수 호출");
	}

	public void login() {
		System.out.println("login함수 호출");
	}

	public void user() {
		System.out.println("user함수 호출");
	}
	
	public void hello() {
		System.out.println("hello함수 호출");
	}
}
