package com.springbook.biz;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

public class UserServiceClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1. Spring 컨테이너를 구동
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. Spring 컨테이너로부터 UserServiceImpl객체를 룩업한다.
		UserService userService = (UserService)container.getBean("userService");
		
		//3.로그인 기능 테스트
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test123");
		
				
		UserVO user = userService.getUser(vo);
				
		
		if(user!=null){
			System.out.println("--------> " + user.getName());			
		}else{
			System.out.println("로그인실패");
		}
		
		container.close();
		
	}

}
