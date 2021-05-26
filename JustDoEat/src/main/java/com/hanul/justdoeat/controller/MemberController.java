package com.hanul.justdoeat.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanul.justdoeat.command.IdcheckCommand;
import com.hanul.justdoeat.command.JoinCommand;
import com.hanul.justdoeat.command.KakaoLoginCommand;
import com.hanul.justdoeat.command.LoginCommand;
import com.hanul.justdoeat.command.MemberCommand;
import com.hanul.justdoeat.command.MemberModifyCommand;
import com.hanul.justdoeat.dao.MemberDAO;
import com.hanul.justdoeat.dto.MemberDTO;

@Controller
public class MemberController {
	
	MemberCommand command;
	
	@RequestMapping(value="/memberModify", method = {RequestMethod.GET, RequestMethod.POST})	
	public String memberModify(HttpServletRequest req, Model model) {
		
		System.out.println("memberModify들어왔다");
 
		// 클라이언트(안드로이드)에서 보낸 데이터 받기
				String m_pw = (String) req.getParameter("m_pw");
				String m_name = (String) req.getParameter("m_name");
				String m_phone = (String) req.getParameter("m_phone");
				String m_gender = (String) req.getParameter("m_gender");
				String m_nikname = (String) req.getParameter("m_nikname");
				String m_address1 = (String) req.getParameter("m_address1");
				String m_address2 = (String) req.getParameter("m_address2");
				String m_email = (String) req.getParameter("m_email");
				
				
				System.out.println(m_pw);
				System.out.println(m_name);
				System.out.println(m_phone);
				System.out.println(m_gender);
				System.out.println(m_nikname);
				System.out.println(m_address1);
				System.out.println(m_address2);
				System.out.println(m_email);
				
				// 받은 데이터 model에 넣기
				model.addAttribute("m_pw", m_pw);
				model.addAttribute("m_name", m_name);
				model.addAttribute("m_phone", m_phone);
				model.addAttribute("m_gender", m_gender);
				model.addAttribute("m_nikname", m_nikname);
				model.addAttribute("m_address1", m_address1);
				model.addAttribute("m_address2", m_address2);
				model.addAttribute("m_email", m_email);
				
				// command 만들기
				command = new MemberModifyCommand();
				command.execute(model);
				
				return "memberModify";
	}
	
	@RequestMapping(value="/memberLogin", method = {RequestMethod.GET, RequestMethod.POST})	
	public String Login(HttpServletRequest req, Model model) {
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
	
	@RequestMapping(value="/id_check", method = {RequestMethod.GET, RequestMethod.POST})	
	public String id_check(HttpServletRequest req, Model model) {
		System.out.println("id_check들어왔다");
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String u_id = (String)req.getParameter("m_id");
		  
			/*
			 * model.addAttribute("u_id", u_id);
			 * 
			 * command = new IdcheckCommand(); command.execute(model);
			 */
		  
		  MemberDAO dao = new MemberDAO();
		  model.addAttribute("num", dao.id_check(u_id));
		 
		
		return "idcheck";
	}
	
	@RequestMapping(value="/email_check", method = {RequestMethod.GET, RequestMethod.POST})	
	public String email_check(HttpServletRequest req, Model model) {
		System.out.println("email_check들어왔다");
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String u_email = (String)req.getParameter("m_email");
		
		/*
		 * model.addAttribute("u_id", u_id);
		 * 
		 * command = new IdcheckCommand(); command.execute(model);
		 */
		
		MemberDAO dao = new MemberDAO();
		model.addAttribute("num", dao.email_check(u_email));
		
		
		return "emailcheck";
	}
	
	@RequestMapping(value="/kakaoMemberLogin", method = {RequestMethod.GET, RequestMethod.POST})	
	public String kakaoLogin(HttpServletRequest req, Model model) {
		System.out.println("kakaoMemberLogin들어왔다");
 
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String m_email = (String)req.getParameter("m_email");
		
		System.out.println(m_email);
		
		model.addAttribute("m_email", m_email);
		
		command = new KakaoLoginCommand();
		command.execute(model);
		
		return "kakaoMemberLogin";
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
