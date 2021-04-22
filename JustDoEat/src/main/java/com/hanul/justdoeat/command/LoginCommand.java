package com.hanul.justdoeat.command;

import org.springframework.ui.Model;

import com.hanul.justdoeat.dao.MemberDAO;
import com.hanul.justdoeat.dto.MemberDTO;

public class LoginCommand implements EatCommand {

	@Override
	public void execute(Model model) {
		String u_id = (String) model.asMap().get("u_id");
		String u_pw = (String) model.asMap().get("u_pw");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.Login(u_id, u_pw);
		
		model.addAttribute("memberLogin", dto);
	}

}
