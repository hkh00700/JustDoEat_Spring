package com.hanul.justdoeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hanul.justdoeat.dto.MemberDTO;

public class MemberDAO {

	DataSource dataSource;
	
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam");
		} catch (NamingException e) {
			e.getMessage();
		}
		
	}//practiceDAO()
	
	
	public int memberModify(String m_pw, String m_name, String m_phone, String m_gender, String m_nikname, String m_address1, String m_address2, String m_email) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = -100;
		
		try {
			connection = dataSource.getConnection();
			String query = "update member set m_pw = ?, m_name = ?, m_phone = ?, m_gender = ?, m_nikname = ?, m_addr1 = ?, m_addr2 = ? where m_email = ?";
			prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, m_pw);
			prepareStatement.setString(2, m_name);
			prepareStatement.setString(3, m_phone);
			prepareStatement.setString(4, m_gender);
			prepareStatement.setString(5, m_nikname);
			prepareStatement.setString(6, m_address1);
			prepareStatement.setString(7, m_address2);
			prepareStatement.setString(8, m_email);
			state = prepareStatement.executeUpdate();
			
			if (state > 0) {
				System.out.println(state + "수정성공");				
			} else {
				System.out.println(state + "수정실패");
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
		
	public MemberDTO Login(String id, String pw) {
	
		MemberDTO dto = null;
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from member where m_id = '" + id + "' and m_pw = '" + pw + "' ";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				String m_id = resultSet.getString("m_id");
				String m_name = resultSet.getString("m_name");
				String m_phone = resultSet.getString("m_phone");
				String m_gender = resultSet.getString("m_gender"); 
				String m_email = resultSet.getString("m_email"); 
				String m_nikname = resultSet.getString("m_nikname"); 
			

				dto = new MemberDTO(m_id, m_name, m_phone, m_gender, m_email, m_nikname);			
			}	
			System.out.println("MemberDTO id : " + dto.getM_id());
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
		
		return dto;
		
	}


	// 카카오 로그인
	public MemberDTO kakaoLogin(String m_emailin) {
		
		MemberDTO dto = null;
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from member where m_email = '" + m_emailin + "'" ;
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				
				String m_gender = resultSet.getString("m_gender"); 
				String m_email = resultSet.getString("m_email"); 
				String m_nikname = resultSet.getString("m_nikname"); 
	
				dto = new MemberDTO(m_gender, m_email, m_nikname);							
			}	
			System.out.println("MemberDTO kakao email : " + dto.getM_email());
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
		
		return dto;
		
	}
	
	//회원가입하기
	public int memberJoin(String m_id, String m_pw, String m_name, String m_phone, String m_gender, String m_email, String m_nikname) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = -100;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into member(m_uq, m_id, m_pw, m_name, m_phone, m_gender, m_email, m_nikname) " + 
			               "values(seq_member.NEXTVAL,'" + m_id + "', '" + m_pw + "', '" + m_name + "', '" + m_phone + "', '" + m_gender + "', '" + m_email + "', '" + m_nikname + "' )";
			prepareStatement = connection.prepareStatement(query);
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
	
}
