package com.hanul.justdoeat.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.justdoeat.command.GameCommand;
import com.hanul.justdoeat.dao.GameDAO;
import com.hanul.justdoeat.dto.GameDTO;

public class eatSelectRandomCommand implements GameCommand {

	@Override
	public ArrayList<GameDTO> execute(Model model) {
		GameDAO edao = new GameDAO();
		ArrayList<GameDTO> edtos= edao.eatSelectRandom();
		
		return edtos;
	}



}
