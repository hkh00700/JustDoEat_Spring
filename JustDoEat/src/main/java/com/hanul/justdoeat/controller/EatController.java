package com.hanul.justdoeat.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanul.justdoeat.dao.RestrantDAO;
import com.hanul.justdoeat.dto.RestaurantDTO;

@Controller
public class EatController {

	@RequestMapping("/restrantaddr")
	public String restrantaddr(Model model, HttpServletRequest req) {
		System.out.println("·¹½ºÅä¶û db");
//		RestrantDTO dto = new RestrantDTO();
		RestrantDAO dao = new RestrantDAO();
		ArrayList<RestaurantDTO> r = dao.restrant();

		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();

		for (int i = 0; i < r.size(); i++) {
			System.out.println(r.get(i).getRestaurant());
			System.out.println(r.get(i).getR_hardness());
			System.out.println(r.get(i).getR_latitude());
			System.out.println(r.get(i).getR_address());
			System.out.println(r.get(i).getR_tel());
			System.out.println(r.get(i).getR_menu());
			System.out.println(r.get(i).getR_imgpath());

		}

		model.addAttribute("restaurant", r);
		req.setAttribute("r", r);

		return "restaurant";
	}

}
