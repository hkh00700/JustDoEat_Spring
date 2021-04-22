package com.hanul.justdoeat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.justdoeat.command.EatCommand;
import com.hanul.justdoeat.command.JoinCommand;

@Controller
public class MemberController {
	
	EatCommand command;
	
	@RequestMapping(value="/memberJoin", method = {RequestMethod.GET, RequestMethod.POST} ) 
	public String memberJoin(HttpServletRequest req, Model model){
		
		System.out.println("���Դ�");
		
		// Ŭ���̾�Ʈ(�ȵ���̵�)���� ���� ������ �ޱ�
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
		
		// ���� ������ model�� �ֱ�
		model.addAttribute("m_id", m_id);
		model.addAttribute("m_pw", m_pw);
		model.addAttribute("m_name", m_name);
		model.addAttribute("m_phone", m_phone);
		model.addAttribute("m_gender", m_gender);
		model.addAttribute("m_email", m_email);
		model.addAttribute("m_nikname", m_nikname);
		
		// command �����
		command = new JoinCommand();
		command.execute(model);
		
		return "memberJoin";
	}
	
	
	
	
}
