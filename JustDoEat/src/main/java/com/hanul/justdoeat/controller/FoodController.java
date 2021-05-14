package com.hanul.justdoeat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.justdoeat.command.FoodCommand;
import com.hanul.justdoeat.dao.FoodDAO;
import com.hanul.justdoeat.dao.Foodimg;

@Controller
public class FoodController {
	
	
	//À½½Ä ÃßÃµ
	@RequestMapping(value="/recommand", method = {RequestMethod.GET, RequestMethod.POST})
	public String foodRecommand(HttpServletRequest req, Model model ) {
		FoodCommand foodcommand = new FoodCommand();
		Foodimg testimg = new Foodimg();
		FoodDAO dao = new FoodDAO();
		
		System.out.println("recommand()");
		
		try {
			req.setCharacterEncoding("UTF-8");

		} catch (Exception e) {
			e.getMessage();
			System.out.println("FoodCommandR() Exception!!! \n" + e.getMessage());
		}
		
		String name = foodcommand.excute(model);
		String response = testimg.requstAPI(name);
		String imgurl = dao.material(response);
		
		model.addAttribute("imgurl", imgurl);
		model.addAttribute("name", name);
		return "recommandFood";
	}
		
	@RequestMapping("/testImg")
	public String testimg(Model model) {
		Foodimg testimg = new Foodimg();
		String text = "±èÄ¡Âî°³";
		String response = testimg.requstAPI(text);
		model.addAttribute("response", response);
		return "testimg_json";
	}
}

