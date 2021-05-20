package com.hanul.jstdoeat.Service;

import org.springframework.ui.Model;

import com.hanul.justdoeat.dto.FoodRandomDTO;

public interface FoodService {
	public FoodRandomDTO excute(Model model);
}
