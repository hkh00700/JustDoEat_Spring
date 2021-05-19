package com.hanul.justdoeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	 //회원 알러지 정보 가져오기
	 public String m_allergylist(String m_nikname){
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 String allergy = "";
		 
		 try {
			 conn = dataSource.getConnection();
			 String sql = "select m_allergy from member where m_nikname = ? ";
			 
			 ps = conn.prepareStatement(sql);
			 ps.setString(1, m_nikname);
			 
			 rs = ps.executeQuery();
			 System.out.println(rs);
			
			 while(rs.next()) {
				 allergy = rs.getString("m_allergy");
			 }
			 
			 System.out.println(m_nikname + "의 알러지 : " + allergy );
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("m_allergylist Exception!!!");
		} finally {
				try {
					if(rs != null)
					rs.close();
					if(ps != null) ps.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		 
		 
		 return allergy;
	 }
	 
	 public int memberModify(String m_nikname, String m_allergy) {
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			int state = -100;
			
			try {
				connection = dataSource.getConnection();
				String query = "update member set m_allergy = ? where m_nikname = ?";
				
				prepareStatement = connection.prepareStatement(query);
				prepareStatement.setString(1, m_allergy);
				prepareStatement.setString(2, m_nikname);
				
				System.out.println("nikname: " + m_nikname);
				System.out.println("allergy: " + m_allergy);
				
				state = prepareStatement.executeUpdate();
				
				if (state > 0) {
					System.out.println(state + "삽입성공");				
				} else {
					System.out.println(state + "삽입실패");
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
			
			return "삽입완료";
			
		}

}