package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LogoutController {

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		// TODO Auto-generated method stub
		System.out.println("�α׾ƿ� ó��");		
		
		//HttpSession session = request.getSession();
		session.invalidate();
		
//	 	ModelAndView mav = new ModelAndView();
//	 	mav.setViewName("redirect:login.jsp");
//	 	return mav;
		return "login.jsp";
	}

}
