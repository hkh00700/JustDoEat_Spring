package com.hanul.justdoeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hanul.justdoeat.dto.RestrantDTO;

public class RestrantDAO {
	
	DataSource dataSource;
	
	public RestrantDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/bteam");
		} catch (NamingException e) {
			e.getMessage();
		}
		
	}//DAO()
	
	public ArrayList<RestrantDTO> restrant() {
		
		ArrayList<RestrantDTO> list = new ArrayList<RestrantDTO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RestrantDTO dto = new RestrantDTO();
		
		
		try {
			conn = dataSource.getConnection();
			String sql = "select * from restrantadd";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto.setRestrant(rs.getString("restrant"));
				dto.setR_latitude(rs.getFloat("r_latitude"));
				dto.setR_hardness(rs.getFloat("R_hardness"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
				try {
					if(rs != null)	rs.close();
					if(ps != null)	ps.close();
					if(conn != null) conn.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		
		return list;
	}
	
}
