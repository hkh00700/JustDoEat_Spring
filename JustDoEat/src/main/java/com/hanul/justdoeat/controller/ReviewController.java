package com.hanul.justdoeat.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.hanul.justdoeat.command.ReviewCommand;
import com.hanul.justdoeat.command.ReviewDeleteCommand;
import com.hanul.justdoeat.command.ReviewInsertCommand;
import com.hanul.justdoeat.command.ReviewSelectCommand;
import com.hanul.justdoeat.command.ReviewUpdateCommand;
import com.hanul.justdoeat.command.ReviewUpdateNoCommand;

@Controller
public class ReviewController {
	
	ReviewCommand command;
	
	@RequestMapping(value="/ReviewSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String userReviewSelect(HttpServletRequest req, Model model) {
		System.out.println("ReviewSelect");
		command = new ReviewSelectCommand();
		command.execute(model);
		
		return "ReviewSelect";
	}
	
	@RequestMapping(value="/ReviewInsert", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String userReviewInsert(HttpServletRequest req, Model model) {
		System.out.println("ReviewInsert");
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} 	
		
		String s_title = (String) req.getParameter("s_title");
		String s_content = (String) req.getParameter("s_content");
		String s_photo_path = (String) req.getParameter("s_photo_path");
		
		
		
		System.out.println("s_title : " + s_title);
		System.out.println("s_conten : " + s_content);
		System.out.println("s_photo_path : " + s_photo_path);
		
		model.addAttribute("s_title", s_title);
		model.addAttribute("s_content", s_content);
		model.addAttribute("s_photo_path", s_photo_path);
		System.out.println("s_id : " + req.getParameter("s_id"));
		model.addAttribute("s_id", req.getParameter("s_id"));
		
		//파일저장 부분
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = multi.getFile("image");
			
	
		if(file != null) {
			
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			
			// 디렉토리 존재하지 않으면 생성
			makeDir(req);	
				
			if(file.getSize() > 0){			
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/");
				
				System.out.println( fileName + " : " + realImgPath);
				System.out.println( "fileSize : " + file.getSize());					
												
			 	try {
			 		// 이미지파일 저장
					file.transferTo(new File(realImgPath, fileName));										
				} catch (Exception e) {
					e.printStackTrace();
				} 
									
			}else{
				// 이미지파일 실패시
				fileName = "FileFail.jpg";
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/" + fileName);
				System.out.println(fileName + " : " + realImgPath);
						
			}			
			
		}
				
		
		command = new ReviewInsertCommand();
		command.execute(model);
		System.out.println("s_title : " + s_title );
		return "ReviewInsert";
				
	}
	
	//폴더 만들기
	public void makeDir(HttpServletRequest req){
		File f = new File(req.getSession().getServletContext()
				.getRealPath("/resources"));
		if(!f.isDirectory()){
		f.mkdir();
		}	
	}
	
	@RequestMapping(value="/ReviewUpdate", method = {RequestMethod.GET, RequestMethod.POST})
	public void anUpdateMulti(HttpServletRequest req, Model model){
		System.out.println("ReviewUpdate()");	
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		String s_title = (String) req.getParameter("s_title");
		String s_content = (String) req.getParameter("s_content");
		String dbImgPath = (String) req.getParameter("dbImgPath");
		String pDbImgPath = (String) req.getParameter("pDbImgPath");
		String no = (String) req.getParameter("no");
		System.out.println(no);
		
		System.out.println(s_title);
		System.out.println(s_content);
		System.out.println("ReviewUpdate:dbImgPath " + dbImgPath);
		System.out.println("ReviewUpdate:pDbImgPath " + pDbImgPath);
		model.addAttribute("s_title", s_title);
		model.addAttribute("s_content", s_content);
		model.addAttribute("dbImgPath", dbImgPath);
		model.addAttribute("no", no);
		
		
		if(pDbImgPath != null) {
			
		
		// 이미지가 서로 같으면 삭제하지 않고 다르면 기존이미지 삭제
		if(!dbImgPath.equals(pDbImgPath)){			
			
			String pFileName = req.getParameter("pDbImgPath").split("/")[req.getParameter("pDbImgPath").split("/").length -1];
			String delDbImgPath = req.getSession().getServletContext().getRealPath("/resources/" + pFileName);
			
			File delfile = new File(delDbImgPath);
			System.out.println(delfile.getAbsolutePath());
			
	        if(delfile.exists()) {
	        	boolean deleteFile = false;
	            while(deleteFile != true){
	            	deleteFile = delfile.delete();
	            }	            
	            
	        }//if(delfile.exists())
		
		}//if(!dbImgPath.equals(pDbImgPath))  
		
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = null;
		
		file = multi.getFile("image");
			
		if(file != null) {
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			
			// 디렉토리 존재하지 않으면 생성
			makeDir(req);	
				
			if(file.getSize() > 0){			
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/");
				
				System.out.println( fileName + " : " + realImgPath);
				System.out.println( "fileSize : " + file.getSize());					
												
			 	try {
			 		// 이미지파일 저장
					file.transferTo(new File(realImgPath, fileName));						
				} catch (Exception e) {
					e.printStackTrace();
				} 
									
			}else{
				fileName = "FileFail.jpg";
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/" + fileName);
				System.out.println(fileName + " : " + realImgPath);
						
			}			
			
		}
		
		}
		
		command = new ReviewUpdateCommand();
		command.execute(model);		
		
	}
	
	
	
	@RequestMapping(value="/ReviewUpdateNo", method = {RequestMethod.GET, RequestMethod.POST})
	public void anUpdateMultiNo(HttpServletRequest req, Model model){
		System.out.println("ReviewUpdateNo");	
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		String s_title = (String) req.getParameter("s_title");
		String s_content = (String) req.getParameter("s_content");
		String no = (String) req.getParameter("no");
		System.out.println(no);
		
		System.out.println(s_title);
		System.out.println(s_content);
		
		model.addAttribute("s_title", s_title);
		model.addAttribute("s_content", s_content);
		model.addAttribute("no", no);
		
		command = new ReviewUpdateNoCommand();
		command.execute(model);		
		
	}
	
	@RequestMapping(value="/ReviewDelete", method = {RequestMethod.GET, RequestMethod.POST})
	public void anDeleteMulti(HttpServletRequest req, Model model){
		System.out.println("ReviewDelete()");		
		
		model.addAttribute("no", req.getParameter("no"));		
				
		System.out.println((String)req.getParameter("id"));
		System.out.println((String)req.getParameter("delDbImgPath"));
		
		if((String)req.getParameter("delDbImgPath") != null) {
			
		
		String pFileName = req.getParameter("delDbImgPath").split("/")[req.getParameter("delDbImgPath").split("/").length -1];
		String delDbImgPath = req.getSession().getServletContext().getRealPath("/resources/" + pFileName);		
		
		// 이미지 파일지우기
		File delfile = new File(delDbImgPath);
		System.out.println(delfile.getAbsolutePath());
		
        if(delfile.exists()) {
            System.out.println("Sub1Del:pDelImagePath " + delfile.exists());
            boolean deleteFile = false;
            while(deleteFile != true){
            	deleteFile = delfile.delete();
            }     
        }
        
		}
		
		command = new ReviewDeleteCommand();
		command.execute(model);	
		
	}
	
	
	
	
	
}


