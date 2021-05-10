package com.hanul.justdoeat.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.justdoeat.dao.ReviewDAO;
import com.hanul.justdoeat.dto.MemberReviewDTO;

public class ReviewSelect implements ReviewCommand {
	@Override
	public void execute(Model model) {			
		ReviewDAO adao = new ReviewDAO();
		ArrayList<MemberReviewDTO> adtos = adao.reviewSelect();
		
		model.addAttribute("ReviewSelect", adtos); 
		
	}
}
