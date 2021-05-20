package com.hanul.justdoeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hanul.justdoeat.dto.FoodRandomDTO;


public class FoodDAO {
	//DB연결하는 클래스 
	DataSource dataSource;
	
	public FoodDAO() {
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam");
		} catch (NamingException e) {
			e.getMessage();
		}
		
	}
	
	public FoodRandomDTO RandomResult() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		FoodRandomDTO dto = new FoodRandomDTO();
		
		
		int ran = (int)(Math.random()*16);
		
		String sql = "";
		if(ran > 0 && ran < 10) {
			sql = "select * from (select * from food_0" +ran + " order by dbms_random.random()) where rownum <= 1 ";
			System.out.println("ran" + ran);
		}else if(ran > 10){
			sql = "select * from (select * from food_" +ran + " order by dbms_random.random()) where rownum <= 1 ";
			System.out.println("ran" + ran);
		}
		
		String foodName = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				dto.setFood(rs.getString("food"));
				dto.setMaterial(rs.getString("Material"));
			}
			System.out.println("추천 음식 이름 : " + dto.getFood());
			System.out.println("추천 음식 재료 : " + dto.getMaterial());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("RandomResult() Exception!!!");
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("RandomResult()/dbCloser() Exception!!!");
			}
				
			
		}
		
		return dto;
	}
	
	public String material(String response) {
		JSONObject object = new JSONObject(response);	
		String items = object.get("items").toString();
		System.out.println(":items: "+ items);
		
		JSONArray ob2 = new JSONArray(items);
		JSONObject imglink = ob2.getJSONObject(0);
		String link = imglink.get("link").toString();
		System.out.println(link + " : link");
		return link;
	}
	
	
}
