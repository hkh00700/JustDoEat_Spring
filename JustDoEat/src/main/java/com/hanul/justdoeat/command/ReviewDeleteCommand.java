package com.hanul.justdoeat.command;

import org.springframework.ui.Model;

import com.hanul.justdoeat.dao.ReviewDAO;

public class ReviewDeleteCommand implements ReviewCommand {

	@Override
	public void execute(Model model) {
		int no = Integer.parseInt((String)model.asMap().get("no"));
		
		
		ReviewDAO adao = new ReviewDAO();
		adao.ReviewDelete(no);
		
	}
}
