package com.hanul.justdoeat.command;

import org.springframework.ui.Model;

import com.hanul.justdoeat.dao.MemberDAO;

public class MemberModifyCommand implements MemberCommand {

	@Override
	public void execute(Model model) {
		String m_pw = (String) model.asMap().get("m_pw");
		String m_name = (String) model.asMap().get("m_name");
		String m_phone = (String) model.asMap().get("m_phone");
		String m_gender = (String) model.asMap().get("m_gender");
		String m_nikname = (String) model.asMap().get("m_nikname");
		String m_address1 = (String) model.asMap().get("m_address1");
		String m_address2 = (String) model.asMap().get("m_address2");
		String m_email = (String) model.asMap().get("m_email");
		
		MemberDAO dao = new MemberDAO();
		
		int state = dao.memberModify(m_pw, m_name, m_phone, m_gender, m_nikname, m_address1, m_address2, m_email);
		
		model.addAttribute("memberModify", String.valueOf(state));
		
	}
	
	
	

}
