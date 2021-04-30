<%@page import="com.hanul.justdoeat.dto.GameDTO"%>


<%@page import="com.google.gson.Gson" %>  


<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<%
	Gson gson = new Gson();
	String json = gson.toJson((ArrayList<GameDTO>)request.getAttribute("edtos"));
	String newJson = json.replace("[", "").replace("{\"food\":", "").replace("}", "").replace("\"", "").replace("]", "");
	
	out.println(newJson);
	
 %>
<%-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>음식이름 </h3>
<c:forEach var="i" items="${edtos}">
${i.food }
</c:forEach>
</body>
</html> --%>