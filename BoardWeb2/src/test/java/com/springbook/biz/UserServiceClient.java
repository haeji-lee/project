package com.springbook.biz;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

public class UserServiceClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1. Spring �����̳ʸ� ����
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. Spring �����̳ʷκ��� UserServiceImpl��ü�� ����Ѵ�.
		UserService userService = (UserService)container.getBean("userService");
		
		//3.�α��� ��� �׽�Ʈ
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test123");
		
				
		UserVO user = userService.getUser(vo);
				
		
		if(user!=null){
			System.out.println("--------> " + user.getName());			
		}else{
			System.out.println("�α��ν���");
		}
		
		container.close();
		
	}

}
