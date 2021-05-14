package com.hanul.justdoeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AllergyListDAO {
	
	DataSource dataSource;
	
	public AllergyListDAO() {
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam");
		} catch (NamingException e) {
			e.getMessage();
		}
		
	}
	
	public ArrayList<String> allergylist() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<String> list = new ArrayList<String>();
		
 		String sql = "select a_material from allergy ";
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String a_material = rs.getString("a_material");
				list.add(a_material);
			}
		} catch (Exception e) {
			System.out.println("AllergyListDAO" + e.getMessage());
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
		
		return list;
	}

}
