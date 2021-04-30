package com.hanul.justdoeat.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.justdoeat.dto.GameDTO;

public interface GameCommand {
	public ArrayList<GameDTO> execute(Model model);
}
