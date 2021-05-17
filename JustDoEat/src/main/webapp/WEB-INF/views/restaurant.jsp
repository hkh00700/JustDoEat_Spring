<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<c:forEach items="${restaurant}" var="r">
${r.restaurant},${r.r_latitude},${r.r_hardness} <br/> 
</c:forEach>

