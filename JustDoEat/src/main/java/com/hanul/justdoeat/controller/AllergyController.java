package com.hanul.justdoeat.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanul.justdoeat.command.AllergyCommand;
import com.hanul.justdoeat.command.MemberModifyCommand;

@Controller
public class AllergyController {
	
	//���� �˷����� ����Ʈ ��������
	@RequestMapping(value="/allergySearchlist", method = {RequestMethod.GET, RequestMethod.POST})
	public String allergylist(HttpServletRequest req, Model model) {
		AllergyCommand allergyCommand = new AllergyCommand();
		
		try {
			req.setCharacterEncoding("UTF-8");

		} catch (Exception e) {
			e.getMessage();
			System.out.println("FoodCommandR() Exception!!! \n" + e.getMessage());
		}
		
		ArrayList<String> list = allergyCommand.allergylist(model);
		model.addAttribute("list", list);
		return "allergyList";
	}
	
	//���� �˷����� ���� �����ͺ��̽��� �����ϱ�
	
	@ResponseBody @RequestMapping(value="/allergyInsert", method = {RequestMethod.GET, RequestMethod.POST})	
	public void memberModify(HttpServletRequest req, Model model) {
		
		System.out.println("memberModify���Դ�");
 
		// Ŭ���̾�Ʈ(�ȵ���̵�)���� ���� ������ �ޱ�
				String m_id = (String) req.getParameter("m_id");
				String m_allergy = (String) req.getParameter("m_allergy");
								
				
				System.out.println(m_id);
				System.out.println(m_allergy);
				
				// ���� ������ model�� �ֱ�
				//model.addAttribute("m_id", m_id);
				//model.addAttribute("m_allergy", m_allergy);
				
				// command �����
				AllergyCommand command = new AllergyCommand();
			
				int result = command.m_allergylist_insert(m_id, m_allergy);
				System.out.println(result + ": result");
			
	}


}
