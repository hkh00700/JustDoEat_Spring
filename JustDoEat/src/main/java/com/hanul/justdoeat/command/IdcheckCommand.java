package com.hanul.justdoeat.command;

import org.springframework.ui.Model;

import com.hanul.justdoeat.dao.MemberDAO;
import com.hanul.justdoeat.dto.MemberDTO;

public class IdcheckCommand implements MemberCommand {

	@Override
	public void execute(Model model) {
		String u_id = (String) model.asMap().get("u_id");
		
		MemberDAO dao = new MemberDAO();
		int num = dao.id_check(u_id);
		
		model.addAttribute("num", num);
	}

}
