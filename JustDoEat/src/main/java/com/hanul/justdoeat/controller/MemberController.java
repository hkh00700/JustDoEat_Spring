package com.hanul.justdoeat.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.justdoeat.command.JoinCommand;
import com.hanul.justdoeat.command.LoginCommand;
import com.hanul.justdoeat.command.MemberCommand;

@Controller
public class MemberController {
	
	MemberCommand command;
	
	@RequestMapping(value="/memberLogin", method = {RequestMethod.GET, RequestMethod.POST})	
	public String anLogin(HttpServletRequest req, Model model) {
		System.out.println("memberLogin들어왔다");
 
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String u_id = (String)req.getParameter("id");
		String u_pw = (String)req.getParameter("pw");
		
		System.out.println(u_id);
		System.out.println(u_pw);
		
		model.addAttribute("u_id", u_id);
		model.addAttribute("u_pw", u_pw);
		
		command = new LoginCommand();
		command.execute(model);
		
		return "memberLogin";
	}
	
	
	
	@RequestMapping(value="/memberJoin", method = {RequestMethod.GET, RequestMethod.POST} ) 
	public String memberJoin(HttpServletRequest req, Model model){
		
		System.out.println("들어왔다");
		
		// 클라이언트(안드로이드)에서 보낸 데이터 받기
		String m_id = (String) req.getParameter("m_id");
		String m_pw = (String) req.getParameter("m_pw");
		String m_name = (String) req.getParameter("m_name");
		String m_phone = (String) req.getParameter("m_phone");
		String m_gender = (String) req.getParameter("m_gender");
		String m_email = (String) req.getParameter("m_email");
		String m_nikname = (String) req.getParameter("m_nikname");
		
		System.out.println(m_id);
		System.out.println(m_pw);
		System.out.println(m_name);
		System.out.println(m_phone);
		System.out.println(m_gender);
		System.out.println(m_email);
		System.out.println(m_nikname);
		
		// 받은 데이터 model에 넣기
		model.addAttribute("m_id", m_id);
		model.addAttribute("m_pw", m_pw);
		model.addAttribute("m_name", m_name);
		model.addAttribute("m_phone", m_phone);
		model.addAttribute("m_gender", m_gender);
		model.addAttribute("m_email", m_email);
		model.addAttribute("m_nikname", m_nikname);
		
		// command 만들기
		command = new JoinCommand();
		command.execute(model);
		
		return "memberJoin";
	}
	
	
	
	
}
