package com.hanul.justdoeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


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
	
	public String RandomResult() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from (select food from food_14 order by dbms_random.random()) where rownum <= 1 ";
		
		String foodName = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				foodName = rs.getString("food");
			}
			System.out.println("추천 음식 이름 : " + foodName);
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
		
		return foodName;
	}
	
	
}
