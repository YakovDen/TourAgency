<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<style>
h4 {	
	padding: 10px;
	font-weight: bolder !important;
	font-size: 20px !important;
	font-family: calibri !important;
	color: #000 !important; 	
	margin:25px 0 0 0 !important;             
}
</style>
</head>
<h4>
	 ${userInfo.userBean.firstName}
	&nbsp;
	${userInfo.userBean.lastName}
	&nbsp;
	(${userInfo.userBean.userName}), <spring:message code="currentUser"/>: <fmt:formatDate pattern="yyyy-MM-dd" value="${userInfo.userBean.currentDate}" />	 
		
</h4>
<br>