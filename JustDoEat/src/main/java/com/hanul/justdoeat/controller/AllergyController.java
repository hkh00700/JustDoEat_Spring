package com.hanul.justdoeat.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.justdoeat.command.AllergyCommand;

@Controller
public class AllergyController {
	
	//음식 알레르기 리스트 가져오기 - 소분류
	@RequestMapping(value="/allergySearchlist", method = {RequestMethod.GET, RequestMethod.POST})
	public String allergylist(HttpServletRequest req, Model model) {
		AllergyCommand allergyCommand = new AllergyCommand();
		
		try {
			req.setCharacterEncoding("UTF-8");

		} catch (Exception e) {
			e.getMessage();
			System.out.println("FoodCommandR() Exception!!! \n" + e.getMessage());
		}
		
		ArrayList<String> list = allergyCommand.allergylist(model);
		model.addAttribute("list", list);
		return "allergyList";
	}


}
