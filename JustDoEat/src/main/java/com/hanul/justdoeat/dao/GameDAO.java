package com.hanul.justdoeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hanul.justdoeat.dto.GameDTO;

public class GameDAO {
	DataSource dataSource;
	
	//DB연결 드라이버 초기화
	public GameDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam");
			
		}catch(NamingException e) {
			e.getMessage();
		}
		
	}
	
	//5개 추천음식 DB에서 가져오기
	public ArrayList<GameDTO> eatSelectRandom() {
		ArrayList<GameDTO> edtos = new ArrayList<GameDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		
		 String sql =
		 "SELECT * FROM (SELECT food FROM FOODAll ORDER BY DBMS_RANDOM.RANDOM()) WHERE ROWNUM <= 32"
		 ;
		
		
		
		
		/*
		 * int ran = (int)(Math.random()*16);
		 * 
		 * String sql = ""; if(ran < 10) { sql =
		 * "select * from (select food from food_0" +ran +
		 * " order by dbms_random.random()) where rownum <= 1 "; }else { sql =
		 * "select * from (select food from food_" +ran +
		 * " order by dbms_random.random()) where rownum <= 1 ";
		 * 
		 * }
		 */
		
		
		
		
		
		try {
			connection = dataSource.getConnection();
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			
			String data = "";
			
			while(resultSet.next()) {				
				//data = data + resultSet.getString("food") + ",";
				
				 GameDTO dto = new GameDTO(); dto.setFood(resultSet.getString("food"));
				 edtos.add(dto);				 
					
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("eatSelectRandom() Exception!!!!");
		}
		
		return edtos;
	}



}
