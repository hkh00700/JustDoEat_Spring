package com.hanul.justdoeat.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanul.justdoeat.dao.RestrantDAO;
import com.hanul.justdoeat.dto.RestrantDTO;

@Controller
public class EatController {
	
	@RequestMapping("/restrantaddr")
	public String restrantaddr(Model model, HttpServletRequest req ) {
		System.out.println("·¹½ºÅä¶û db");
		RestrantDTO dto = new RestrantDTO();
		RestrantDAO dao = new RestrantDAO();
		ArrayList<RestrantDTO> r = dao.restrant();
		
		
		
		model.addAttribute("r", r);
		req.setAttribute("r", r);
		
		
		
		return "restrant";
	}

}
