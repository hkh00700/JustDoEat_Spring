package com.hanul.justdoeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
