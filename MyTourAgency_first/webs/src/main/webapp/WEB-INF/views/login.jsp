<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<!-- <title>Insert title here</title> -->


<style type="text/css">
.formLogin {
	font-family: 'Ubuntu', 'Lato', sans-serif;
	font-weight: 400;
	/* Размер и положение */
	width: 300px;
	position: relative;
	margin: 60px auto 30px;
	padding: 10px;
	overflow: hidden;
	/* Стили */
	background: #111;
	border-radius: 0.4em;
	border: 1px solid #191919;
	box-shadow: inset 0 0 2px 1px rgba(255, 255, 255, 0.08), 0 16px 10px
		-8px rgba(0, 0, 0, 0.6);
}

.formLogin label {
	/* Размер и положение */
	width: 50%;
	float: left;
	padding-top: 9px;
	/* Стили*/
	color: #ddd;
	font-size: 12px;
	text-transform: uppercase;
	letter-spacing: 1px;
	text-shadow: 0 1px 0 #000;
	text-indent: 10px;
	font-weight: 700;
	cursor: pointer;
}

.formLogin input[type=text], .formLogin input[type=password] {
	/* Размер и положение */
	width: 50%;
	float: left;
	padding: 8px 5px;
	margin-bottom: 10px;
	font-size: 12px;
	/* Стили */
	background: linear-gradient(#1f2124, #27292c);
	border: 1px solid #000;
	box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1);
	border-radius: 3px;
	/* Стили шрифтов */
	font-family: 'Ubuntu', 'Lato', sans-serif;
	color: #fff;
}

.formLogin input[type=text]:hover, .formLogin input[type=password]:hover,
	.formLogin label:hover ~ input[type=text], .formLogin label:hover ~ input[type=password]
	{
	background: #27292c;
}

.formLogin input[type=text]:focus, .formLogin input[type=password]:focus {
	box-shadow: inset 0 0 2px #000;
	background: #494d54;
	border-color: #51cbee;
	outline: none; /* Убираем внешние линии в Chrome */
}

.formLogin p:nth-child(3), .formLogin p:nth-child(4) {
	float: left;
	width: 50%;
}

.formLogin label[for=remember] {
	width: auto;
	float: none;
	display: inline-block;
	text-transform: capitalize;
	font-size: 11px;
	font-weight: 400;
	letter-spacing: 0px;
	text-indent: 2px;
}

.formLogin input[type=checkbox] {
	margin-left: 10px;
	vertical-align: middle;
}

.formLogin input[type=submit] {
	/* Ширина и положение */
	width: 100%;
	padding: 8px 5px;
	/* Стили */
	border: 1px solid #0273dd; /* Откат */
	border: 1px solid rgba(0, 0, 0, 0.4);
	box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.3), inset 0 10px 10px
		rgba(255, 255, 255, 0.1);
	border-radius: 3px;
	background: #38a6f0;
	cursor: pointer;
	/* Стили шрифтов */
	font-family: 'Ubuntu', 'Lato', sans-serif;
	color: white;
	font-weight: 700;
	font-size: 15px;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.8);
}

.formLogin input[type=submit]:hover {
	box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.6);
}

.formLogin input[type=submit]:active {
	background: #287db5; <
	box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.6);
	border-color: #000; /* Откат */
	border-color: rgba(0, 0, 0, 0.9);
}

.no-boxshadow .formLogin input[type=submit]:hover {
	background: #2a92d8;
}

/* Линия градиента */
.formLogin:after {
	/* Размер и положение */
	content: "";
	height: 1px;
	width: 33%;
	position: absolute;
	left: 20%;
	top: 0;
	/* Стили */
	background: linear-gradient(left, transparent, #444, #b6b6b8, #444, transparent);
}

/* Маленькая вспышка */
.formLogin:before {
	/* Размер и положение */
	content: "";
	width: 8px;
	height: 5px;
	position: absolute;
	left: 34%;
	top: -7px;
	/* Стили*/
	border-radius: 50%;
	box-shadow: 0 0 6px 4px #fff;
}

.formLogin p:nth-child(1):before {
	/* Размер и положение */
	content: "";
	width: 250px;
	height: 100px;
	position: absolute;
	top: 0;
	left: 45px;
	/* Стили */
	transform: rotate(75deg);
	background: linear-gradient(50deg, rgba(255, 255, 255, 0.15),
		rgba(0, 0, 0, 0));
	pointer-events: none;
}

.no-pointerevents .formLogin p:nth-child(1):before {
	display: none;
}

.formLogin a{
	text-decoration:none;
	color:white;
	font-size:12px;
	float:left;
	padding-left:20px;	
}
.formLogin a:hover{
	color:#777;
}

</style>

</head>
<body bgcolor="gainsboro">
	<br>
	<div>
		<c:if test="${not empty exception}">
			<font size="2" color="red"><b><em>${exception}</em></b></font>
		</c:if>
	</div>
	<br>

	<br />
	<div>
		<c:if test="${not empty error}">
			<font size="2" color="red"><b><em>${error}</em></b></font>
		</c:if>
	</div>
	
	
	<div>
		<c:if test="${not empty errorEmpty}">
			<font size="2" color="red"><b><em>${errorEmpty}</em></b></font>
		</c:if>
	</div>

<script type="text/javascript">
function validate() {
     if (document.formLogin.login.value == "" && document.formLogin.password.value == "") {
        alert("USERNAME and PASSWORD are required");
        document.formLogin.login.focus();
        return false;
    } 
    if (document.formLogin.login.value == "") {
        alert("Please enter USERNAME.");
        document.formLogin.login.focus();
        return false;
    }
    if (document.formLogin.password.value == "") {
    alert("Please Enter your secret PASSWORD….");
    document.formLogin.password.focus();
        return false;
    }
   
    return true;
}
</script>

	<div class="formLogin">
	<c:url value="/login" var="loginUrl" />		
		<form name="formLogin" action="${loginUrl}" method="POST">		
			<div>
				<label for="login"><spring:message code="label.username"/></label>
				<input type="text" name="login"	id="login" placeholder="Username"/>								 
			</div>

			<div class="clearfix">
				<label for="password"><spring:message code="label.password"/></label>
				<input type="password" name="password" id="password" placeholder="Password"/>				
			</div>

			<p class="clearfix">
				<input type="checkbox" name="remember" id="remember"> <label
					for="remember"><spring:message code="label.remember"/></label>
			</p>
			<p class="clearfix">
				<input type="submit" name="submit" value="<spring:message code="label.entry"/>" onclick="if (!validate()){return false;}">						
			</p>
			
			<div><a href="custom-auth.html"><spring:message code="label.registration"/></a></div>

		</form>
	</div>
</body>
</html>
