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

import com.hanul.justdoeat.dto.RestaurantDTO;


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
	
	public ArrayList<RestaurantDTO> restrant() {
		
		ArrayList<RestaurantDTO> list = new ArrayList<RestaurantDTO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		try {
			conn = dataSource.getConnection();
			String sql = "select * from restaurantaddr";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				RestaurantDTO dto = new RestaurantDTO();
				dto.setRestaurant(rs.getString("restaurant"));
				dto.setR_latitude(rs.getFloat("r_latitude"));
				dto.setR_hardness(rs.getFloat("r_hardness"));
				dto.setR_address(rs.getString("r_address"));
				dto.setR_tel(rs.getString("r_tel"));
				dto.setR_menu(rs.getString("r_menu"));
				dto.setR_imgpath(rs.getString("r_imgpath"));
				
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
