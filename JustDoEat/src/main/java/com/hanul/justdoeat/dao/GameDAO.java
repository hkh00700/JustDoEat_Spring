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
	
	//DB���� ����̹� �ʱ�ȭ
	public GameDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam");
			
		}catch(NamingException e) {
			e.getMessage();
		}
		
	}
	
	//5�� ��õ���� DB���� ��������
	public ArrayList<GameDTO> eatSelectRandom() {
		ArrayList<GameDTO> edtos = new ArrayList<GameDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		String sql = "SELECT * FROM (SELECT food FROM FOOD_05  ORDER BY DBMS_RANDOM.RANDOM()) WHERE ROWNUM <= 32";
		
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
