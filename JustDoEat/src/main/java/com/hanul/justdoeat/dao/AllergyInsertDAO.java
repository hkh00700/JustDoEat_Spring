package com.hanul.justdoeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hanul.justdoeat.dto.AllergyInsertDTO;

public class AllergyInsertDAO {
	
	DataSource dataSource;
	
	 public AllergyInsertDAO() {
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam");
		} catch (NamingException e) {
			e.getMessage();
		}
		
	}

	 public int memberModify(String m_id, String m_allergy) {
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			int state = -100;
			
			try {
				connection = dataSource.getConnection();
				String query = "update member set m_allergy = ? where m_id = ?";
				
				prepareStatement = connection.prepareStatement(query);
				prepareStatement.setString(1, m_allergy);
				prepareStatement.setString(2, m_id);
				
				System.out.println("id: " + m_id);
				System.out.println("allergy: " + m_allergy);
				
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
	 
	 	public String allergyinsert(String m_id, String m_allergy) {
			
	 		AllergyInsertDTO dto = null;
	 		Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;
			
			try {
				connection = dataSource.getConnection();
				String query = "select * from member where m_id = '" + m_id + "' and m_allergy = '" + m_allergy ;
				prepareStatement = connection.prepareStatement(query);
				resultSet = prepareStatement.executeQuery();
	 	
				while(resultSet.next()) {
					
				dto = new AllergyInsertDTO(m_id, m_allergy);
	 	}
				System.out.println("AllergyInsertDTO id : " + dto.getM_id());
			} catch (Exception e) {
				
			}finally {
				try {
					if(resultSet != null) {
						resultSet.close();
					}
					if(prepareStatement != null) {
						prepareStatement.close();
					}
					if(connection != null) {
						connection.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					
				}
			}
			
			return "»ðÀÔ¿Ï·á";
			
		}

}