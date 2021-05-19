package com.hanul.justdoeat.command;

import org.springframework.ui.Model;

import com.hanul.jstdoeat.Service.FoodService;
import com.hanul.justdoeat.dao.FoodDAO;
import com.hanul.justdoeat.dto.FoodRandomDTO;

public class FoodCommand implements FoodService {

	@Override
	public FoodRandomDTO excute(Model model) {
		FoodDAO fdao = new FoodDAO();
		return fdao.RandomResult();
		
	}

}
