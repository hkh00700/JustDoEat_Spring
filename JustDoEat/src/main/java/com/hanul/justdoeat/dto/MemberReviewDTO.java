package com.hanul.justdoeat.dto;

public class MemberReviewDTO {
	
		private int no;
	    private String s_title;
	    private String s_content;
	    private String s_photo_path;
	    private String s_nikname;
		
	    public MemberReviewDTO(int no, String s_title, String s_content, String s_photo_path, String s_nikname) {
			super();
			this.no = no;
			this.s_title = s_title;
			this.s_content = s_content;
			this.s_photo_path = s_photo_path;
			this.s_nikname = s_nikname;
		}
	    
	    

		public MemberReviewDTO(String s_title, String s_content, String s_photo_path) {
			super();
			this.s_title = s_title;
			this.s_content = s_content;
			this.s_photo_path = s_photo_path;
		}



		public int getNo() {
			return no;
		}

		public void setNo(int no) {
			this.no = no;
		}

		public String getS_title() {
			return s_title;
		}

		public void setS_title(String s_title) {
			this.s_title = s_title;
		}

		public String getS_content() {
			return s_content;
		}

		public void setS_content(String s_content) {
			this.s_content = s_content;
		}

		public String getS_photo_path() {
			return s_photo_path;
		}

		public void setS_photo_path(String s_photo_path) {
			this.s_photo_path = s_photo_path;
		}

		public String getS_nikname() {
			return s_nikname;
		}

		public void setS_nikname(String s_nikname) {
			this.s_nikname = s_nikname;
		}
	
	    
}
