package com.hanul.justdoeat.command;

import org.springframework.ui.Model;

import com.hanul.justdoeat.dao.MemberDAO;
import com.hanul.justdoeat.dto.MemberDTO;

public class KakaoLoginCommand implements MemberCommand {

	@Override
	public void execute(Model model) {
		String m_email = (String) model.asMap().get("m_email");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.kakaoLogin(m_email);
		
		model.addAttribute("kakaoMemberLogin", dto);
	}

}
