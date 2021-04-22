package com.hanul.justdoeat.dto;

public class MemberDTO {
String m_id, m_pw, m_name, m_phone, m_gender, m_email, m_nikname;
	
	// 데이터베이스에 멤버변수 추가할 때
	public MemberDTO(String m_id, String m_pw, String m_name, String m_phone, String m_gender, String m_email,
			String m_nikname) {
		super();
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_phone = m_phone;
		this.m_gender = m_gender;
		this.m_email = m_email;
		this.m_nikname = m_nikname;
	}

	// 로그인할 때 비밀번호 없이 멤버변수 보낼 때
	public MemberDTO(String m_id, String m_name, String m_phone, String m_gender, String m_email, String m_nikname) {
		super();
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_phone = m_phone;
		this.m_gender = m_gender;
		this.m_email = m_email;
		this.m_nikname = m_nikname;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_pw() {
		return m_pw;
	}

	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	public String getM_gender() {
		return m_gender;
	}

	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_nikname() {
		return m_nikname;
	}

	public void setM_nikname(String m_nikname) {
		this.m_nikname = m_nikname;
	}
}
