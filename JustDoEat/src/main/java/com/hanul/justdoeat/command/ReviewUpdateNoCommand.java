package com.hanul.justdoeat.command;

import org.springframework.ui.Model;

import com.hanul.justdoeat.dao.ReviewDAO;

public class ReviewUpdateNoCommand implements ReviewCommand {
	@Override
	public void execute(Model model) {
		
		String s_title = (String)model.asMap().get("s_title");
		String s_content = (String)model.asMap().get("s_content");
		int no = Integer.parseInt((String)model.asMap().get("no"));
		
		ReviewDAO adao = new ReviewDAO();
		
		adao.ReviewUpdateNo(s_title, s_content, no);
		
	}
	
}
