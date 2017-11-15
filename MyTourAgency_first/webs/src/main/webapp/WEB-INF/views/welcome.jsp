<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<body>
<head>
<style>
body {
	background: #c7b39b
}
</style>
</head>
Current Locale : ${pageContext.response.locale}
<div style="text-align: center">
	<h1>
		<spring:message code="title" />
	</h1>
	<br>
	<br>
	<c:url value="web-content/pic/travel.jpg" var="imgUrl" />
	<img src="${imgUrl}" hspace="20" />

</div>

<br />

<div style="margin-top: 0px"></div>
<div style="margin-left: 10px">
	<security:authorize access="hasAuthority('administrator')">
		<c:url value="web-content/pic/administrator.jpg" var="imgAdminUrl" />
		<a href="touragent.html"><img src="${imgAdminUrl}" hspace="20" /></a>
		<spring:message code="admin" />
	</security:authorize>	
</div>

<div style="margin-top: -120px"></div>
<div style="margin-left: 1050px">
	<c:url value="web-content/pic/user.jpg" var="imgUserUrl" />
	<a href="customer.html"><img src="${imgUserUrl}" hspace="20" /></a>
	<spring:message code="user" />
</div>
<br />
</body>