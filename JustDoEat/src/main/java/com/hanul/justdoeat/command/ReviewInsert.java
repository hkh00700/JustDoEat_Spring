package com.hanul.justdoeat.command;

import org.springframework.ui.Model;

import com.hanul.justdoeat.dao.ReviewDAO;

public class ReviewInsert implements ReviewCommand {
	@Override
	public void execute(Model model) {
		
		
		String s_title = (String)model.asMap().get("s_title");
		String s_content = (String)model.asMap().get("s_content");			
		String s_photo_path = (String)model.asMap().get("s_photo_path");
		
		ReviewDAO dao = new ReviewDAO();
		dao.reviewInsert(s_title, s_content, s_photo_path );
			
	}	 
}
