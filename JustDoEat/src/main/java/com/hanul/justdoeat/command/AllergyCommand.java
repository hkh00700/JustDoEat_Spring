package com.hanul.justdoeat.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.jstdoeat.Service.AllergyLIstService;
import com.hanul.justdoeat.dao.AllergyInsertDAO;
import com.hanul.justdoeat.dao.AllergyListDAO;

public class AllergyCommand implements AllergyLIstService {

	@Override
	public ArrayList<String> allergylist(Model model) {
		AllergyListDAO allergylistdao = new AllergyListDAO();
		return allergylistdao.allergylist();
	}

	@Override
	public int m_allergylist_insert(String m_nikname, String m_allergy) {
		AllergyInsertDAO dao = new AllergyInsertDAO();
		return dao.memberModify(m_nikname, m_allergy);
	
	}

	@Override
	public String m_allergylist(String m_nikname) {
		AllergyInsertDAO dao = new AllergyInsertDAO();
		return dao.m_allergylist(m_nikname);
	}

}
