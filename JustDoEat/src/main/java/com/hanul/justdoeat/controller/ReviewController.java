package com.hanul.justdoeat.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.hanul.justdoeat.command.ReviewCommand;
import com.hanul.justdoeat.command.ReviewInsert;
import com.hanul.justdoeat.command.ReviewSelect;

@Controller
public class ReviewController {
	
	ReviewCommand command;
	
	@RequestMapping(value="/ReviewSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String userReviewSelect(HttpServletRequest req, Model model) {
		System.out.println("ReviewSelect");
		command = new ReviewSelect();
		command.execute(model);
		
		return "ReviewSelect";
	}
	
	@RequestMapping(value="/ReviewInsert", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String userReviewInsert(HttpServletRequest req, Model model) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
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
				
		
		command = new ReviewInsert();
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
	
}


