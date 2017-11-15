<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>  

   <%--  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/webapp/web-content/css/styles.css" type="text/css"> --%>
 <link href="${contextPath}/web-content/css/styles.css" rel="stylesheet">
    <title>
        <spring:message code="registration.title" />
    </title>
 
</head>
<body>
<div id="form_wrapper" class="form_wrapper">
<form:form class="register"  action="custom-auth.html" modelAttribute="userForm" method="POST">
     <h3><spring:message code="registration.title" /></h3>
		<div class="column">
			<div>
				<label><spring:message code="label.firstname"/></label>				
				<form:input type="text" path="firstName" placeholder="firstname" style="text-align: center"/>
				<form:errors path="firstName" cssClass="has-error" />				
			</div>
			<div>
				<label><spring:message code="label.lastname"/></label>				
				<form:input type="text" path="lastName" placeholder="lastname" style="text-align: center"/>
				<form:errors path="lastName" cssClass="has-error" />				
			</div>

		</div>
		<div class="column">
			<div>
				<label><spring:message code="label.username"/></label>
				<form:input type="text" path="userName" placeholder="username" style="text-align: center"/>
				<form:errors path="userName" cssClass="has-error" />
			</div>

			<div>
				<label><spring:message code="label.password"/></label>				
				<form:input type="password" path="password" placeholder="password" style="text-align: center"/>	
				<form:errors path="password" cssClass="has-error" />			
			</div>

		</div>
		<div class="bottom">
			<input type="submit" value="<spring:message code="registration.title" />"/>
			<a href="login.html"><spring:message code="label.authorization"/></a>			
		</div>
		
	</form:form>
	</div>
	
</body>
</html>