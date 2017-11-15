<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.Date" %>

<!-- ========================================= TABLE : TOURS ========================================= -->

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style type="text/css">
 h1 {
 	position: relative;	
 	background-color: rgb(214,86,43);
 	top: 15px !important;
 	text-align: center; /* Выравнивание по центру */
	margin: auto !important; 
	padding: 10px; 
	width:1500px;    
    color: rgba(255,255,255,.9);              
   } 
body {
	font: 11pt Arial, Helvetica, sans-serif;
	margin: 0px;
	padding: 5px;
	background: #7b7; /* Цвет фона */
	color: #000; /* Цвет текста */
}

table {
	border-collapse: collapse; /* Отображать двойные линии как одинарные */
}

caption {
	font-family: annabelle, cursive;
	font-weight: bold;
	font-size: 2em;
	padding: 10px;
	color: #F3CD26;
	text-shadow: 1px 1px 0 rgba(0, 0, 0, .3);
}

th {
	background: #ccc; /* Цвет фона */	
}

a {
	font-style: oblique;
	min-width: 30px; 
	max-width: 320px;
}

td, th {
	border: 1px solid #800; /* Параметры границы */
	padding: 4px; /* Поля в ячейках */
	text-align: center; /* Выравнивание по центру */
}
</style>
</head>
<h1>
	<spring:message code="adminTitle"/>
</h1>
<table border="1px">
	<caption>
		<spring:message code="adminNameTour"/>
	</caption>
	<tr bgcolor="#ccc">
		<th><spring:message code="table.id"/></th>
		<th><spring:message code="table.dateBegin"/></th>
		<th><spring:message code="table.dateEnd"/></th>
		<th><spring:message code="table.name"/></th>
		<th><spring:message code="table.country"/></th>
		<th><spring:message code="table.description"/></th>
		<th><spring:message code="table.city"/></th>
		<th><spring:message code="table.cityDescription"/></th>
		<th><spring:message code="table.numberNights"/></th>
		<th><spring:message code="table.cost"/></th>
		<th><spring:message code="table.discount"/></th>		
		<th><spring:message code="table.typeTour"/></th>		
		<th><spring:message code="table.kindTour"/></th>		
	</tr>
	<c:forEach var="p" items="${requestScope.tours}">
		<tr>
			<td><c:out value="${p.id_tour}" />
			<td><c:out value="${p.dateOfBeginning}" /></td>
			<td><c:out value="${p.dateEnd}" /></td>
			<td><c:out value="${p.name}" /></td>
			<td><c:out value="${p.country.name}" /></td>
			<td><c:out value="${p.country.description}" /></td>
			<td><c:out value="${p.city.name}" /></td>
			<td><c:out value="${p.city.description}" /></td>
			<td><c:out value="${p.numberOfNights}" /></td>
			<td><c:out value="${p.cost}" /></td>
			<td><c:out value="${p.discount}" /></td>
			<td><c:out value="${p.typeOfTour.typeOfTour}" /></td>
			<td><c:out value="${p.kindOfTour.kindOfTour}" /></td>
			<td>
				<p>
					<a href="delete-tour.html?idTour=${p.getId_tour()}" onclick="return confirm('Are you sure?')"><spring:message
							code="table.delete" /></a>
				</p>
			</td>
		</tr>
	</c:forEach>
</table>
<br>  
<!-- ========================================= PAGINATION : TOURS ========================================= -->
<table border="1" cellpadding="5" cellspacing="5">
	<tr>
		<%--For displaying Previous link except for the 1st page --%>
		<c:if test="${paginationCreatorTourInfo.currentPage > 1}">
			<td>
				<a href="creator-tour_previous-page.html"><spring:message code="previous"/></a>
			</td>
		</c:if>

	    <%--For displaying Page numbers. The when condition does not display a link for the current page--%>
        <c:forEach begin="1" end="${paginationCreatorTourInfo.noOfPages}" var="i">
            <c:choose>
                <c:when test="${paginationCreatorTourInfo.currentPage == i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="creator-tour_go-to-page.html?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>

		<%--For displaying Next link --%>
		<c:if test="${paginationCreatorTourInfo.currentPage < paginationCreatorTourInfo.noOfPages}">
		    <td>
		    	<a href="creator-tour_next-page.html"><spring:message code="next"/></a>
		    </td>
		</c:if>
	</tr>
</table> 
<br /><br />
<!-- ========================================= ADD : TOUR ========================================= -->
<head>
<style type="text/css">
.headerAddTour {
	position: absolute;
	top: 600px;
	left: 80px;
	color: #F3CD26;
	text-shadow: 1px 1px 0 rgba(0, 0, 0, .3);	
}

input:focus {
	background: #FA6;
	outline: none; /* убрать рамку */
}

.my::-webkit-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.my::-moz-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.my::-ms-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

input {
	width: 130px;
	margin-left: 10px;
}

.field {
	position:relative;
	top: 90px;
	left: 10px;
	clear: both;
	text-align: right;
	<strong>line-height: 25px;
}

label {
	float: left !important;
	<strong>padding-right: 10px !important;
}

.main {
	float: left !important;
}

/* form input[type=submit] { 
	color: blue;
	margin-left: 40px;
	cursor: pointer;  
	position: absolute;
	left: -20px;
	top: 970px;
	background: silver;
	width: 75px;  	
}
 */
/* form .formSubmit {  *//* класс для ие6 */
/* 	margin-left: 65px;
	cursor: pointer;
}
 */
 .buttonAdd {
	position: absolute;
	height: 100px;	
	top: 970px;
	left: 10px;
}

.button {
	font-family: Georgia;
	font-style: italic;
	font-weight: 300;
	font-size: 15px;
	color: #0000FF;
	border : 1px #696969 solid;
	border-radius: 45px;
	background-color: #C0C0C0;
	text-shadow: 4px 4px -4px #0000FF;
	padding: 6px important;	
	box-shadow: 0 3px 10px rgba(0,0,0,.25);
	inset: 0 2px 0 rgba(255,255,255,.6);	
}

.button:hover {
	cursor: pointer;
	background-color: #00FF00;
	box-shadow: inset 0 0 10px rgba(0,0,0,.2),
  0 2px 0 rgba(255,255,255,.4),
  inset 0 2px 0 rgba(0,0,0,.1);
}
</style>
</head>


<script>
	function checkNight(dateOfBeginning, dateOfEnd) {

		var numberOfNights;
		// The number of milliseconds in one day
		var oneDay = 24 * 60 * 60 * 1000;
		
		var firstDate = document.getElementById('dateOfBeginning').value;;
		var secondDate = document.getElementById('dateOfEnd').value;
	
	if (firstDate == "" || secondDate == "") {
			alert("Пожалуйста, введите даты заезда и выезда!");
			return false;
		}
		// Calculate the difference in milliseconds
		numberOfNights = Math.abs(Date.parse(firstDate)	- Date.parse(secondDate)) / oneDay;

		document.getElementById('numberNights').value = numberOfNights;
		return true;
	}
</script>



<form:form action="add-tour.html" modelAttribute="tour" method="post">

	<div class="headerAddTour">
		<h2>
			<spring:message code="adminAddTour" />
		</h2>
	</div>
	
	<strong><div class="main"></strong>	 
	<div class="field"> 	
		<label for="n"><spring:message code="table.dateBegin"/>:</label>			
		<form:input style="width: 135px" id = "dateOfBeginning" type="date" name="calendar" path = "dateOfBeginning"/>		
	</div>	
	 
	<div class="field">
		<label for="n"><spring:message code="table.dateEnd"/>:</label>   
        <form:input style="width: 135px" id = "dateOfEnd" type="date" name="calendar" path = "dateEnd"/>     
	</div> 
	
	<div class="field">
		<label for="n"><spring:message code="table.name"/>:</label>
		<form:input style="width: 135px" class="my" path="name" placeholder="Name"/>
	</div>

	<div class="field">
		<label for="n"><spring:message code="table.country" />:</label>
		<form:select path="country" style="width: 137px">
			<form:option value="-1" label="Выбрать страну"/>
			<form:options items="${countriesBean}" itemValue="countryId" itemLabel="name" />
		</form:select>
	</div>

	<div class="field">
		<label for="n"><spring:message code="table.city" />:</label>
		<form:select path="city" style="width: 137px">
			<form:option value="-1" label="Выбрать город" />
			<form:options items="${citiesBean}" itemValue="cityId"
				itemLabel="name" />
		</form:select>
	</div>
	
	<div class="field">		
		<!--<form:input path="numberOfNights" />-->		
		<input style="width: 137px" type="button" value="<spring:message code="clickButton"/>" onclick="if (!checkNight()){return false;}"/><br>				
	</div>
	
	<div class="field">	
		<label for="n"><spring:message code="table.numberNights"/>:</label>			
		<form:input style="width: 135px" id = "numberNights" type ="number" path = "numberOfNights"/>		
	</div>
	
	<div class="field">
		<label for="n"><spring:message code="table.cost"/>:</label>
		<form:input style="width: 135px" path="cost"/>
	</div>
	
	<div class="field">
		<label for="n"><spring:message code="table.discount"/>:</label>
		<form:input style="width: 135px" class="my" path="discount" placeholder="Discount %"/>
	</div>

	<div class="field">
		<label for="n"><spring:message code="table.typeTour"/>:</label>
		<form:select path="typeOfTour" style="width: 137px">
			<form:option value="-1" label="Выбрать тип тура" />
			<form:options items="${typeOfTours}" itemValue="id_typeOfTour" itemLabel="typeOfTour"/>
		</form:select>
	</div>
 
	<div class="field">
		<label for="n"><spring:message code="table.kindTour"/>:</label>
		<form:select path="kindOfTour" style="width: 137px">
			<form:option value="-1" label="Выбрать вид тура" />
			<form:options items="${kindOfTours}" itemValue="id_kindOfTour" itemLabel="kindOfTour"/>
		</form:select>		
	</div>
	
	<strong></div></strong>
	<div class = "buttonAdd">
		<form>
			<input type="hidden" name="operation" value="addtour">			
			<button class = "button"><spring:message code="add"/></button>
		</form>
	</div>
</form:form>
<!-- ========================================= TABLE : DISCOUNT FOR CLIENTS ========================================= -->
<head>
<style type="text/css">
caption {
	font-family: annabelle, cursive;
	font-weight: bold;
	font-size: 2em;
	padding: 10px;
	color: #F3CD26;
	text-shadow: 1px 1px 0 rgba(0, 0, 0, .3);
}

#mainTable {
	width: 50%;
	height: 50%;
	position: absolute !important;
	left: 10px !important;
	top: 1080px !important;
}

#mainTable td {
	text-align: center;
	vertical-align: middle;
}

th {
	background: #ccc; /* Цвет фона */
	text-align: center; /* Выравнивание по центру */
}

</style>
</head>
<body>
	<table id="mainTable" border="1px">
		<caption>
			<spring:message code="adminCustomerBooked"/>
		</caption>
		<tr bgcolor="#ccc">
			<th><spring:message code="table.id"/></th>
			<th><spring:message code="table.firstName"/></th>
			<th><spring:message code="table.lastName"/></th>
			<th><spring:message code="table.nameCost"/></th>
			<th><spring:message code="table.discount"/></th>
			<th><spring:message code="table.discountDelete"/></th>
		</tr>

		<c:forEach var="t" items="${requestScope.clients}">
			<tr>
				<td><c:out value="${t.id_user}"/>
				<td><c:out value="${t.firstName}"/></td>
				<td><c:out value="${t.lastName}"/></td>
				<td><c:out value="${t.getToursNames()}"/></td>
				<td><c:out value="${t.discountBytour}"/></td>
				<td>
				<p>
					<a href="delete-discount-tour.html?idUser=${t.getId_user()}" onclick="return confirm('Are you sure?')"><spring:message
							code="table.delete" /></a>
				</p>
			</td>
			</tr>
		</c:forEach>
	</table>
</body>

<!-- ========================================= ADD DISCOUNT : FOR CLIENT ========================================= -->
<head>
<style type="text/css">
.headerPosition {
	position: absolute !important;
	top: 1080px !important;
	left: 900px !important;
	color: #F3CD26;
	text-shadow: 1px 1px 0 rgba(0, 0, 0, .3);
}

.idDiscount {
	width: 150px;
	background: #cf0;
	padding: 15px;
	border: solid 1px brown;
	float: left !important;
	position: absolute !important;
	top: 1140px !important;
	left: 820px !important;
}

.myDiscount {
	width: 150px;
	background: #cf0;
	padding: 15px;
	border: solid 1px brown;
	float: left !important;
	position: absolute !important;
	top: 1140px !important;
	left: 1000px !important;
}

/*  .buttonDiscount {
	 position: absolute;
	top: 171px;
	left: 1170px; 		
} */
.buttonDiscount {
	position: absolute;
	height: 100px;	
	top: 1140px;
	left: 1190px;
}

.button {
	font-family: Georgia;
	font-style: italic;
	font-weight: 300;
	font-size: 15px;
	color: #0000FF;
	border : 1px #696969 solid;
	border-radius: 45px;
	background-color: #C0C0C0;
	text-shadow: 4px 4px -4px #0000FF;
	padding: 2px;	
	box-shadow: 0 3px 10px rgba(0,0,0,.25);
	inset: 0 2px 0 rgba(255,255,255,.6);	
}

.button:hover {
	cursor: pointer;
	background-color: #00FF00;
	box-shadow: inset 0 0 10px rgba(0,0,0,.2),
  0 2px 0 rgba(255,255,255,.4),
  inset 0 2px 0 rgba(0,0,0,.1);
}

.idDiscount::-webkit-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.idDiscount::-moz-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.idDiscount::-ms-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.myDiscount::-webkit-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.myDiscount::-moz-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.myDiscount::-ms-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}
</style>
</head>
<form:form action="discount-tour.html" method="post"
	modelAttribute="tourDiscount">

	<div class="headerPosition">
		<h2>
			<spring:message code="discountClient"/>
		</h2>
	</div>

	<div class="idDiscount">		
		<form:input path="userId" size="5" placeholder="№ User" style="text-align: center"/>
		<form:errors  path="userId"  cssClass="error" cssStyle="color: #ff0000; position:relative; left: 25px; top: 10px;display: inline-block;" /> <!-- Сообщить  об -–>
                                                             <!–-  ошибке  в  поле  userId -->
	</div>

	<div class="myDiscount">		
		<form:input path="discountByTour" size="5" placeholder="Discount %" style="text-align: center"/>
		<form:errors  path="discountByTour"  cssClass="error" cssStyle="color: #ff0000; position:relative; left: 25px; top: 10px;display: inline-block;" /> <!-- Сообщить  об -–>
                                                             <!–-  ошибке  в  поле  discountByTour -->
	</div>

	<div class="buttonDiscount">		
		<%-- <input type="submit" value="<spring:message code="discount"/>"> --%>
		<button class = "button"><spring:message code="discount"/></button>
	</div>	
</form:form>
<!-- ========================================= ADD : COUNTRY ========================================= -->
<head>
<style>
.countriesTour {
	position: absolute !important;
	top: 600px !important;
	left: 450px !important;
	color: #F3CD26;
	text-shadow: 1px 1px 0 rgba(0, 0, 0, .3);
}

.nameCountry {
	width: 150px;
	background: #cf0;
	padding: 15px;
	border: solid 1px brown;
	/* float: left !important; */
	position: absolute !important;
	top: 680px !important;
	left: 335px !important;
	display: inline-block;
	overflow: hidden;
}

.descriptionCountry {
	position: absolute;
	top: 680px;
	margin-left: 513px !important;
	height: 220px !important;
	width: 342px !important;
}

.nameCountry::-webkit-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.nameCountry::-moz-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.nameCountry::-ms-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.descriptionCountry::-webkit-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.descriptionCountry::-moz-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.descriptionCountry::-ms-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.buttonAddCountry {
	position: absolute;
	height: 100px;	
	top: 680px;
	left: 757px;
}

.button {
	font-family: Georgia;
	font-style: italic;
	font-weight: 300;
	font-size: 15px;
	color: #0000FF;
	border : 1px #696969 solid;
	border-radius: 45px;
	background-color: #C0C0C0;
	text-shadow: 4px 4px -4px #0000FF;
	padding: 2px;	
	box-shadow: 0 3px 10px rgba(0,0,0,.25);
	inset: 0 2px 0 rgba(255,255,255,.6);	
}

.button:hover {
	cursor: pointer;
	background-color: #00FF00;
	box-shadow: inset 0 0 10px rgba(0,0,0,.2),
  0 2px 0 rgba(255,255,255,.4),
  inset 0 2px 0 rgba(0,0,0,.1);
}
</style>
</head>

<form:form action="add-country.html" method="post"
	modelAttribute="countryBean">

	<div class="countriesTour">
		<h2>
			<spring:message code="countriesTour"/>
		</h2>
	</div>		
	
	<div class="nameCountry">		
		<form:input path="name" size="5" placeholder="Name" style="text-align: center"/>		
		<form:errors  path="name"  cssClass="error" cssStyle="color: #ff0000; position:relative; left: 25px; top: 10px;display: inline-block;" /> <!-- Сообщить  об -–>
                                                             <!–-  ошибке  в  поле  username -->
	</div>	
	
	<div class="descriptionCountry">		
		<form:textarea path="description" name="message" rows="5" cols="30" placeholder="Description" style="text-align: center"/>		
		<form:errors   path="description"  cssClass="error" cssStyle="color: #ff0000; position:relative; left: 25px; top: 10px;display: inline-block;" />  <!-- Сообщить  об -–>
                                                           <!–-  ошибке  в  поле  description -->	
                                                           	  
	</div>

	<div class="buttonAddCountry">		
		<%-- <input style="width: 75px" type="submit" value="<spring:message code="add"/>"> --%>
		<button class = "button"><spring:message code="add"/></button>
	</div>
	
	
	
</form:form>

<!-- ========================================= DELETE : COUNTRY ========================================= -->
<head>
<style type="text/css">
.deleteLabel {
	position: absolute !important;
	top: 600px !important;
	left: 860px !important;
	color: #F3CD26;
	text-shadow: 1px 1px 0 rgba(0, 0, 0, .3);
}

.fieldCountry {
	position: absolute !important;
	top: 680px !important;
	left: 840px !important;
	clear: both;
	text-align: right; <
	strong >line-height: 25px;
}

/* .buttondelCountry {
	position: absolute !important;
	top: -290px !important;
	left: 960px !important;
}

.delete {
	float: left
} */
.buttonDelCountry {
	position: absolute;
	height: 100px;	
	top: 680px;
	left: 990px;
}

.button {
	font-family: Georgia;
	font-style: italic;
	font-weight: 300;
	font-size: 15px;
	color: #0000FF;
	border : 1px #696969 solid;
	border-radius: 45px;
	background-color: #C0C0C0;
	text-shadow: 4px 4px -4px #0000FF;
	padding: 2px;	
	box-shadow: 0 3px 10px rgba(0,0,0,.25);
	inset: 0 2px 0 rgba(255,255,255,.6);	
}

.button:hover {
	cursor: pointer;
	background-color: #00FF00;
	box-shadow: inset 0 0 10px rgba(0,0,0,.2),
  0 2px 0 rgba(255,255,255,.4),
  inset 0 2px 0 rgba(0,0,0,.1);
}
</style>
</head>



<form:form action="delete-country.html" modelAttribute="tour" method="post">

	<div class="deleteLabel">
		<h2>
			<spring:message code="countryDelete" />
		</h2>
	</div>
	
	<div class="fieldCountry">			
			<form:select path="country" style="width: 137px">
				<form:option value="-1" label="Выбрать страну" />
				<%-- <form:options items="${countries}" itemValue="id_country" itemLabel="name" /> --%>
				<form:options items="${countriesBean}" itemValue="countryId" itemLabel="name" />
			</form:select>
		</div>

		<div class="buttonDelCountry">
			<%-- <input type="submit" value="<spring:message code="table.delete"/>" /> --%>
			<button class = "button"><spring:message code="table.delete"/></button>
		</div>
	
</form:form>

<!-- ========================================= ADD : CITY ========================================= -->
<head>
<style>
.citiesTour {
	position: absolute !important;
	top: 800px !important;
	left: 450px !important;
	color: #F3CD26;
	text-shadow: 1px 1px 0 rgba(0, 0, 0, .3);
}

.nameCity {
	width: 150px;
	background: #cf0;
	padding: 15px;
	border: solid 1px brown;
	/* float: left !important; */
	position: absolute !important;
	top:860px !important;
	left: 335px !important;
	display: inline-block;
	overflow: hidden;
}

.descriptionCity {
	position: absolute;
	top: 860px;
	margin-left: 513px !important;
	height: 220px !important;
	width: 342px !important;
}

/* .buttonAddCity {
	position: absolute;
	top: 10px;
	left: 680px;
} */

.nameCity::-webkit-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.nameCity::-moz-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.nameCity::-ms-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.descriptionCity::-webkit-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.descriptionCity::-moz-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.descriptionCity::-ms-input-placeholder {
	color: olive;
	font-style: italic;
	text-align: center; /* Выравнивание по центру */
}

.buttonAddCity {
	position: absolute;
	height: 100px;	
	top: 860px;
	left: 757px;
}

.button {
	font-family: Georgia;
	font-style: italic;
	font-weight: 300;
	font-size: 15px;
	color: #0000FF;
	border : 1px #696969 solid;
	border-radius: 45px;
	background-color: #C0C0C0;
	text-shadow: 4px 4px -4px #0000FF;
	padding: 2px;	
	box-shadow: 0 3px 10px rgba(0,0,0,.25);
	inset: 0 2px 0 rgba(255,255,255,.6);	
}

.button:hover {
	cursor: pointer;
	background-color: #00FF00;
	box-shadow: inset 0 0 10px rgba(0,0,0,.2),
  0 2px 0 rgba(255,255,255,.4),
  inset 0 2px 0 rgba(0,0,0,.1);
}
</style>
</head>

<form:form action="add-city.html" method="post"
	modelAttribute="cityBean">

	<div class="citiesTour">
		<h2>
			<spring:message code="citiesTour"/>
		</h2>
	</div>		
	
	<div class="nameCity">	
		<form:input path="name" size="5" placeholder="Name" style="text-align: center"/>		
		<form:errors  path="name"  cssClass="error" cssStyle="color: #ff0000; position:relative; left: 25px; top: 10px;display: inline-block;" /> <!-- Сообщить  об -–>
                                                             <!–-  ошибке  в  поле  username -->
	</div>	
	
	<div class="descriptionCity">		
		<form:textarea path="description" name="message" rows="5" cols="30" placeholder="Description" style="text-align: center"/>		
		<form:errors   path="description"  cssClass="error" cssStyle="color: #ff0000; position:relative; left: 25px; top: 10px;display: inline-block;" />  <!-- Сообщить  об -–>
                                                           <!–-  ошибке  в  поле  description -->	
                                                           	  
	</div>	

	<div class="buttonAddCity">		
		<%-- <input style="width: 75px" type="submit" value="<spring:message code="add"/>"> --%>
		<button class = "button"><spring:message code="add"/></button>
	</div>
		
</form:form>
<!-- ========================================= DELETE : CITY ========================================= -->
<head>
<style type="text/css">
.deleteCity {
	position: absolute !important;
	top: 600px !important;
	left: 1100px !important;
	color: #F3CD26;
	text-shadow: 1px 1px 0 rgba(0, 0, 0, .3);
}

.fieldCity {
	position: absolute !important;
	top: 680px !important;
	left: 1080px !important;
	clear: both;
	text-align: right; <
	strong >line-height: 25px;
}

/* .delCity {
	position: absolute !important;
	top: -290px !important;
	left: 1200px !important;
}

.delete {
	float: left
} */
.buttonDelCity {
	position: absolute;
	height: 100px;	
	top: 680px;
	left: 1230px;
}

.button {
	font-family: Georgia;
	font-style: italic;
	font-weight: 300;
	font-size: 15px;
	color: #0000FF;
	border : 1px #696969 solid;
	border-radius: 45px;
	background-color: #C0C0C0;
	text-shadow: 4px 4px -4px #0000FF;
	padding: 2px;	
	box-shadow: 0 3px 10px rgba(0,0,0,.25);
	inset: 0 2px 0 rgba(255,255,255,.6);	
}

.button:hover {
	cursor: pointer;
	background-color: #00FF00;
	box-shadow: inset 0 0 10px rgba(0,0,0,.2),
  0 2px 0 rgba(255,255,255,.4),
  inset 0 2px 0 rgba(0,0,0,.1);
}
</style>
</head>



<form:form action="delete-city.html" modelAttribute="tour" method="post">

	<div class="deleteCity">
		<h2>
			<spring:message code="cityDelete" />
		</h2>
	</div>
	
	<div class="fieldCity">			
			<form:select path="city" style="width: 137px">
				<form:option value="-1" label="Выбрать город" />
				<form:options items="${citiesBean}" itemValue="cityId" itemLabel="name" />
			</form:select>
		</div>

		<div class="buttonDelCity">
			<%-- <input type="submit" value="<spring:message code="table.delete"/>" /> --%>
			<button class = "button"><spring:message code="table.delete"/></button>
		</div>
	
</form:form>
</html>