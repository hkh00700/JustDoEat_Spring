package com.hanul.justdoeat.command;

import org.springframework.ui.Model;

import com.hanul.justdoeat.dao.MemberDAO;

public class JoinCommand implements MemberCommand {

	@Override
	public void execute(Model model) {
		String m_id = (String) model.asMap().get("m_id");
		String m_pw = (String) model.asMap().get("m_pw");
		String m_name = (String) model.asMap().get("m_name");
		String m_phone = (String) model.asMap().get("m_phone");
		String m_gender = (String) model.asMap().get("m_gender");
		String m_email = (String) model.asMap().get("m_email");
		String m_nikname = (String) model.asMap().get("m_nikname");
		
		MemberDAO dao = new MemberDAO();
		
		int state = dao.memberJoin(m_id, m_pw, m_name, m_phone, m_gender, m_email, m_nikname);
		
		model.addAttribute("memberJoin", String.valueOf(state));
		
	}
	
}
