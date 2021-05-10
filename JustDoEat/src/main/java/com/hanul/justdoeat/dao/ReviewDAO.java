package com.hanul.justdoeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hanul.justdoeat.dto.MemberReviewDTO;

public class ReviewDAO {
	
	DataSource dataSource;
	
	public ReviewDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam");
		} catch (NamingException e) {
			e.getMessage();
		}
		
	}
	
	public ArrayList<MemberReviewDTO> reviewSelect() {		
		
		ArrayList<MemberReviewDTO> adtos = new ArrayList<MemberReviewDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select s_title, s_content, s_photo_path "					
							+ " from s_board"; 
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				String s_title = resultSet.getString("s_title");
				String s_content = resultSet.getString("s_content");
				
				String s_photo_path = resultSet.getString("s_photo_path"); 

				MemberReviewDTO adto = new MemberReviewDTO(s_title, s_content, s_photo_path);
				adtos.add(adto);			
			}	
			
			System.out.println("adtosÅ©±â" + adtos.size());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}

		return adtos;

	}
	

	
	
	public int reviewInsert(String s_title, String s_content, String s_photo_path) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = -100;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into s_board(s_title, s_content, s_photo_path) " + 
			               "values('" + s_title + "', '" + s_content + "', '" + s_photo_path + "')";
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
			
			if (state > 0) {
				System.out.println(state + "»ðÀÔ¼º°ø");				
			} else {
				System.out.println(state + "»ðÀÔ½ÇÆÐ");
			}
			
		} catch (Exception e) {			
			System.out.println(e.getMessage());
		} finally {
			try {				
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}
		
		
		return state;
	}
	
}
	
	
	

