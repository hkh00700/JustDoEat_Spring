package com.hanul.justdoeat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.justdoeat.command.AllergyCommand;
import com.hanul.justdoeat.command.FoodCommand;
import com.hanul.justdoeat.dao.FoodDAO;
import com.hanul.justdoeat.dao.Foodimg;
import com.hanul.justdoeat.dto.FoodRandomDTO;

@Controller
public class FoodController {
	
	
	//음식 추천
	@RequestMapping(value="/recommand", method = {RequestMethod.GET, RequestMethod.POST})
	public String foodRecommand(HttpServletRequest req, Model model ) {
		
		String m_nikname = (String) req.getParameter("m_nikname");
		String rsponse = null;
		String imgurl = "";
		
		FoodCommand foodcommand = new FoodCommand();
		Foodimg testimg = new Foodimg();
		FoodDAO dao = new FoodDAO();
		
		AllergyCommand command = new AllergyCommand();
		String allergy = command.m_allergylist(m_nikname);
//		
		String food = null;
		
		
		//사용자가 알러지를 가지고 있으면
						
			
		
				
		
		
		
		System.out.println("recommand()");
		
		try {
			req.setCharacterEncoding("UTF-8");

		} catch (Exception e) {
			e.getMessage();
			System.out.println("FoodCommandR() Exception!!! \n" + e.getMessage());
		}
		
		
		//음식추천 값이 널이 아닐때까지 돌리기!
		FoodRandomDTO dto = foodcommand.excute(model);
		while(dto.getFood() == null) { 
			dto = foodcommand.excute(model);
		}
		
		//알러지 있는지 확인하기
		if(allergy != null) {
			String[] allergys = allergy.split(",");
			
			for(int i = 0; i < allergys.length; i++) {
				if(dto.getMaterial() != null) {
					
					while(dto.getMaterial().contains(allergys[i].trim())) {
					dto = foodcommand.excute(model);				
					}
						food = dto.getFood().toString();
				
				}else {
					System.out.println(dto.getFood());
				}
			} 
		} else {
			
			food = dto.getFood().toString();
		}
	
	//	rsponse = testimg.requstAPI(dto.getFood());
	//	String url = "https://blog.kakaocdn.net/dn/dAEQye/btqDOkONLE0/5TK0HtrButojrSUVadJRP0/img.jpg";
		System.out.println(dto.getFood() + "음식추천");
		model.addAttribute("name", food);
	//	model.addAttribute("imgurl", url);
	//	model.addAttribute("rsponse", rsponse);
		
			
		
		
		return "recommandFood";
	}
		

}

