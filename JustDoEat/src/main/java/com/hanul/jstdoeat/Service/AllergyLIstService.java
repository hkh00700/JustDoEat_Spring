package com.hanul.jstdoeat.Service;

import java.util.ArrayList;

import org.springframework.ui.Model;

public interface AllergyLIstService {
	public ArrayList<String> allergylist(Model model);
	public int m_allergylist_insert(String m_nikname, String m_allergy);
	public String m_allergylist(String m_nikname);
	
	

}
