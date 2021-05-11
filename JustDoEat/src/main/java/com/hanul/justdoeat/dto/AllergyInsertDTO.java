package com.hanul.justdoeat.dto;

public class AllergyInsertDTO {
	String m_id, m_allergy;
	
	public AllergyInsertDTO(String m_id, String m_allergy) {
		super();
		this.m_id = m_id;
		this.m_allergy= m_allergy;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_allergy() {
		return m_allergy;
	}

	public void setM_allergy(String m_allergy) {
		this.m_allergy = m_allergy;
	}
	
	
	
	

}
