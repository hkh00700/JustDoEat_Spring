package com.hanul.justdoeat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.justdoeat.command.FoodCommand;

@Controller
public class FoodController {
	
	
	//음식 추천
	@RequestMapping(value="/recommand", method = {RequestMethod.GET, RequestMethod.POST})
	public String foodRecommand(HttpServletRequest req, Model model ) {
		FoodCommand foodcommand = new FoodCommand();
		
		System.out.println("recommand()");
		
		try {
			req.setCharacterEncoding("UTF-8");

		} catch (Exception e) {
			e.getMessage();
			System.out.println("FoodCommandR() Exception!!! \n" + e.getMessage());
		}
		
		String name = foodcommand.excute(model);
		model.addAttribute("name", name);
		return "recommandFood";
	}
		
}

