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

import sun.java2d.opengl.WGLSurfaceData.WGLVSyncOffScreenSurfaceData;

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
			String query = "select * from s_board"; 
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				String s_title = resultSet.getString("s_title");
				String s_content = resultSet.getString("s_content");
				int no = resultSet.getInt("no");
				String s_photo_path = resultSet.getString("s_photo_path"); 
				String s_id = resultSet.getString("s_id");
				MemberReviewDTO adto = new MemberReviewDTO(s_title, s_content, s_photo_path);
				adto.setNo(no);
				adto.setS_id(s_id);
				adtos.add(adto);			
			}	
			
			//System.out.println("adtos크기" + adtos.size());
			
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
	

	
	
	public int reviewInsert(String s_title, String s_content, String s_photo_path, String s_id) {
		System.out.println("s_id : " + s_id);
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = -100;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into s_board(s_title, s_content, s_photo_path, s_id) " + 
			               "values('" + s_title + "', '" + s_content + "', '" + s_photo_path + "', '" +s_id+ "')";
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

	public int ReviewDelete(int no) {
		System.out.println(no);
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;

		try {
			connection = dataSource.getConnection();
			String query = "delete from s_board where no =" + no;
			
			System.out.println(no);

			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();

			if (state > 0) {
				System.out.println("삭제성공");				
			} else {
				System.out.println("삭제실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
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
			}
		}

		return state;

	}

	public int ReviewUpdate(String s_title, String s_content, String dbImgPath, int no) {
		System.out.println(no);
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;
	
		try {			
					
			connection = dataSource.getConnection();
			String query = "update s_board set " 			             
		             + " s_title = '" + s_title + "' "
		             + ", s_content = '" + s_content + "' "
		             + ", s_photo_path = '" + dbImgPath + "' "
					 + " where no = '" + no + "' ";
			
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
	
			if (state > 0) {
				System.out.println("수정성공");
				
			} else {
				System.out.println("수정실패");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
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
	
		return state;
	
	}

	public int ReviewUpdateNo(String s_title, String s_content, int no) {
		System.out.println(no);
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;
	
		try {			
					
			connection = dataSource.getConnection();
			String query = "update s_board set " 			             
		             + " s_title = '" + s_title + "' "
		             + ", s_content = '" + s_content + "' "
					 + " where no = '" + no + "' ";
			
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
	
			if (state > 0) {
				System.out.println("수정성공2");
				
			} else {
				System.out.println("수정실패2");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
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
	
		return state;
	
	}
	
}
	
	
	

