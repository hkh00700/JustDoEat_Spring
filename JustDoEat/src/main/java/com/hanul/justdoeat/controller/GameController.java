package com.hanul.justdoeat.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.justdoeat.command.GameCommand;
import com.hanul.justdoeat.command.eatSelectRandomCommand;
import com.hanul.justdoeat.dao.GameDAO;
import com.hanul.justdoeat.dto.GameDTO;



@Controller
public class GameController {

	GameCommand command;
	
	@RequestMapping(value="/eatSelectRandom", method = {RequestMethod.GET, RequestMethod.POST})
	public String eatSelectRandom(HttpServletRequest req, Model model ) {
		System.out.println("eatSelectRandom()");
		
		command = new eatSelectRandomCommand();
		GameDAO edao = new GameDAO();
		ArrayList<GameDTO> edtos = command.execute(model);
		
		model.addAttribute("edtos", edtos);
		return "GameEat";
		
	}
	
	
	
	
}