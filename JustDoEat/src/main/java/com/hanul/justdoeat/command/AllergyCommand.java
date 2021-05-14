package com.hanul.justdoeat.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.jstdoeat.Service.AllergyLIstService;
import com.hanul.justdoeat.dao.AllergyListDAO;

public class AllergyCommand implements AllergyLIstService {

	@Override
	public ArrayList<String> allergylist(Model model) {
		AllergyListDAO allergylistdao = new AllergyListDAO();
		return allergylistdao.allergylist();
	}

	@Override
	public int m_allergylist_insert(String m_id, String m_allergy) {
		//AllergyInsertDAO allergyinsertdao = new AllergyInsertDAO();
		//return allergyinsertdao.memberModify(m_id, m_allergy);
		return 0;
	}

}
