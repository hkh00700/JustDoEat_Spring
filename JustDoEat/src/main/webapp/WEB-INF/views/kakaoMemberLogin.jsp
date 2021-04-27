<%@page import="com.hanul.justdoeat.dto.MemberDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	 Gson gson = new Gson();
	String json = gson.toJson((MemberDTO)request.getAttribute("kakaoMemberLogin"));
	
	out.println(json);

%>

