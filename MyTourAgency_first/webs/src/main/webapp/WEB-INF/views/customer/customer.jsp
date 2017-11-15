<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<!-- ========================================= LIST : TOURS ========================================= -->
<head>
	<%-- <c:url value="web-content/css/styles.css" var="css_url" />
	<link rel="stylesheet" href="${css_url}"> --%>
 
<style type="text/css">
h1 {
	position: relative;	
	background-color: #32cd32;
	top: 15px !important;
	color: rgba(255, 255, 255, .9);
	padding: 10px;
	text-align: center; /* Выравнивание по центру */
	margin: auto !important; 
    width:1700px;
    font-size: 2em !important;/* Размер надписи */
    font-family: arial!important;    
}

hr {
	position: relative;	
	top: 15px;
 	margin: 5px !important;   
    border: none !important; /* Убираем границу */
    background-color: red !important; /* Цвет линии */
    color: red !important; /* Цвет линии для IE6-7 */
    height: 5px !important; /* Толщина линии */
    width: 1700px !important;    
   }

.holiday {
	text-shadow: 1px 1px 2px black, 0 0 1em red !important; /* Параметры тени */
	text-align: center !important; /* Выравнивание по центру */
	color: white !important; /* Белый цвет текста */
	font-size: 6em !important; /* Размер надписи */
}

u {
	font-family: annabelle, cursive;
	font-weight: bold;
	font-size: 1.5em;
	padding: 10px;
	color: #F3CD26;
	text-shadow: 1px 1px 0 rgba(0, 0, 0, .3);
}

body {
	background: aliceblue;
}

table {
	border-collapse: collapse; /* Отображать двойные линии как одинарные */
}

th {
	background: #ccc; /* Цвет фона */
}

td {
	font-style: oblique;
}

td, th {
	font-size: 22px;
	border: 1px solid #800; /* Параметры границы */
	padding: 4px; /* Поля в ячейках */
	text-align: center; /* Выравнивание по центру */
}

.round-button_off {

    display:block;
    width:43px;
    height:43px;
    line-height:40px;
    border: 3px solid orange;
    border-radius: 50%;
    color:#f5f5f5;
    
  /*   color:red; */
    text-align:center;
    text-decoration:none;
    background:#464646;
    box-shadow: 0 0 3px gray;
    font-size:10px;
    font-weight:italics;
}
.round-button_off:hover {
    background: pink;
}
.round-button_on{

    display:block;
    width:43px;
    height:43px;
    line-height:40px;
    border: 3px solid orange;
    border-radius: 50%;
    
   /*  color:#f5f5f5; */
   color: #900;
  font-weight: bold;
    text-align:center;
    text-decoration:none;
    background: black;
    box-shadow: 0 0 3px gray;
    font-size:10px;
    font-weight:italics;
}

.round-button_off:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}

</style>
</head>
  <!-- define the toggle function -->
     <script language="javascript">
        function change(item){
           if(item.value == "round-button_on") {
              item.value="round-button_off";
           } else {
              item.value="round-button_on";
           }
        }
     </script>

<section>
<hr>
<h1>
	<spring:message code="userTitle" />
</h1>
<hr>
</section>

<div class = "holiday"><spring:message code="holiday"/></div>


<u style="color: #d2691e">
	<spring:message code="listTours" />	
</u>
<br /><br />
<table>
	<tr>
		<th><spring:message code="table.id"/></th>
		<th><spring:message code="table.name"/></th>
		<th><spring:message code="table.country"/></th>
		<th><spring:message code="table.dateBegin"/></th>
		<th><spring:message code="table.dateEnd"/></th>
		<th><spring:message code="table.numberNights"/></th>
		<th><spring:message code="table.cost"/></th>
		<th><spring:message code="table.typeTour"/></th>
		<th><spring:message code="table.kindTour"/></th>        		
	</tr>        	
	<c:forEach var="p" items="${requestScope.toursReserved}"> 
		<tr>
			<td><c:out value="${p.id_tour}"/></td>           		
			<td><c:out value="${p.name}" /></td>
			<td><c:out value="${p.country.name}"/></td>
			<td><c:out value="${p.dateOfBeginning}" /></td>
			<td><c:out value="${p.dateEnd}" /></td>					
			<td><c:out value="${p.numberOfNights}" /></td>
			<td><c:out value="${p.cost}" /></td>
			<td><c:out value="${p.typeOfTour.typeOfTour}"/></td>	
			<td><c:out value="${p.kindOfTour.kindOfTour}"/></td>

			<form:form action="reserve-tour.html" modelAttribute="reservation"	method="post">			
				
				<th>				 			
 					<a href="reserve-tour.html?tourId=${p.id_tour}"
								class="round-button_off" onclick="change(this)" ><spring:message code="choose"/></a>				
 				</th>
			</form:form>
		<tr/>
	</c:forEach>
</table>

<br>  
<!-- ========================================= PAGINATION : TOURS ========================================= -->
<table border="1" cellpadding="5" cellspacing="5">
	<tr>
		<%--For displaying Previous link except for the 1st page --%>
		<c:if test="${paginationInfo.currentPage > 1}">
			<td>
				<a href="previous-page.html"><spring:message code="previous"/></a>
			</td>
		</c:if>

	    <%--For displaying Page numbers. The when condition does not display a link for the current page--%>

        <c:forEach begin="1" end="${paginationInfo.noOfPages}" var="i">
            <c:choose>
                <c:when test="${paginationInfo.currentPage == i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="go-to-page.html?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>

		<%--For displaying Next link --%>
		<c:if test="${paginationInfo.currentPage < paginationInfo.noOfPages}">
		    <td>
		    	<a href="next-page.html"><spring:message code="next"/></a>
		    </td>
		</c:if>
	</tr>
</table> 
<br /><br />
<!-- ========================================= NUMBER OF RESERVED TOURS ========================================= -->
<u style="color: #d2691e"> <spring:message code="currentCustomer"/></u>
<br><br>
<table>
	<tr>
		<th><spring:message code="table.firstName"/></th>
		<th><spring:message code="table.lastName"/></th>
		<th><spring:message code="table.numberTours"/></th>        		        		
	</tr>        	

	<c:forEach var="l" items="${tourForClient}"> 
		<tr>              	
			<td>
				${l.firstName}
			</td>           		
			<td>
				${l.lastName}
			</td>   				
			<td>
				<a href="tourcount.html">
					<c:out value="${l.TourCount()}" />
				</a>
			</td>
		<tr/>
	</c:forEach>
</table>
<!-- ========================================= REFERENS TO HOT TOURS ========================================= -->
<head>

<style type="text/css">

.droplink h3{
	position: absolute;
	top: 10px !important;
	left: 10px !important;
}

/*Сбросим отступы*/
.droplink ul,.droplink h3,.droplink h3 a{
padding:0;margin:0
}
/*Базовая обертка*/
.droplink {
font-size: 1.5em;
width:210px;
position:absolute;
margin:-380px 0 0 1150px !important;
}
/*Стиль блока при наведении*/
.droplink:hover{
height:auto;
background-color:#3E403D !important;
border:solid 1px #3A3C39 !important;
}
/*Заголовок в обычном состоянии*/
.droplink h3 a{
text-align:center;
font-family: annabelle, cursive !important;
text-shadow: 1px 1px 0 rgba(0, 0, 0, .3);
width:100%;
display:block;
padding:0px 0px;
color:#d2691e !important;
text-decoration:none
}
.droplink h3 a img{border:none}

/*Стиль для заголовка при наведении*/
.droplink:hover h3 a {
color:#FFF !important;
font-weight:bold;
position:absolute
}

/*Скрываем список без наведения*/
.droplink ul{
list-style:none;
display:none
}
/*Отображаем список при наведении*/
.droplink:hover ul{
display:block;
margin-top:40px
}
.droplink li{display:block}

/*Стиль элемента списка*/
.droplink li a{
padding:5px 12px 4px 34px;
margin:1px;
background-color:#484A47 !important;
display:block;
color:#FFF !important;
text-decoration:none;
font-size:18px;
background-repeat:no-repeat;
background-position: 10px 8px
}
/*Стиль элемента при наведении*/
.droplink li a:hover{
background-color:#999
}
/*Иконки*/
.hot a {background-image:url("web-content/pic/hot_tour.jpg") !important}
</style> 

</head>

<div class="droplink">
<h3><a href="#"><spring:message code="tours"/></a></h3>
<ul>
<li class="hot"><a href="hot-tour.html" ><spring:message code="hot_tour"/></a></li>
</ul>
</div>

<!-- ========================================= SELECTING THE TOUR LIST ========================================= -->
<head>
<style>
.field {
	width: 150px;
	background: #cf0;
	padding: 5px;
	border: solid 1px brown;
	float: left;
	position: relative;
	top: -170px;
	left: 750px;
}
h3 {	
	position: relative;
	top: -150px;
	left: 780px;
	font-family: annabelle, cursive;
	font-weight: bold;
	font-size: 1.5em;
	text-decoration: underline;
	padding: 10px;
	color: #d2691e;
	text-shadow: 1px 1px 0 rgba(0, 0, 0, .3);
}

form input[type=submit] { /* оформление для кнопки submit */
	position: relative;
    left: 770px;
	top: -155px;	
	font-family: Helvetica, arial, serif;
    color:#333;
    text-align:center;
    font-size:12px;
    cursor: pointer;
    background-color:#f0f0f0;
    text-decoration: none;
    text-shadow: #333 0px -1px 0px;
    padding:10px;
    border: 1px solid rgba(0,0,0,0.5);
    border-bottom: 3px solid rgba(0,0,0,0.5);
    border-radius: 3px;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    box-shadow: 0 2px 8px rgba(0,0,0,0.5), /* Exterior Shadow */
      inset 0 1px rgba(255,255,255,0.3), /* Top light Line */
      inset 0 10px rgba(255,255,255,0.2), /* Top Light Shadow */
      inset 0 10px 20px rgba(255,255,255,0.25), /* Sides Light Shadow */
      inset 0 -15px 30px rgba(0,0,0,0.3); /* Dark Background */
    -webkit-box-shadow: 0 2px 8px rgba(0,0,0,0.5),
      inset 0 1px rgba(255,255,255,0.3),
      inset 0 10px rgba(255,255,255,0.2),
      inset 0 10px 20px rgba(255,255,255,0.25),
      inset 0 -15px 30px rgba(0,0,0,0.3);
    -moz-box-shadow: 0 2px 8px rgba(0,0,0,0.5),
      inset 0 1px rgba(255,255,255,0.3),
      inset 0 10px rgba(255,255,255,0.2),
      inset 0 10px 20px rgba(255,255,255,0.25),
      inset 0 -15px 30px rgba(0,0,0,0.3);	
}

form input[type=submit]:hover {
    opacity: 0.7;
    filter: alpha(opacity=70);
    -moz-opacity:0.7;
    -khtml-opacity: 0.7;
  }
form .formSubmit { /* класс для ие6 */
	margin-left: 65px;
	cursor: pointer;	
}
</style>
</head>

<script>
	function checkDate(dateBeginFrom, dateBeginBefore) {	
		
		var firstDate = document.getElementById('dateBeginFrom').value;;
		var secondDate = document.getElementById('dateBeginBefore').value;
	
	if (firstDate == "" || secondDate == "") {
			alert("Пожалуйста, введите диапазон желаемых дат заезда!");
			return false;
		}
		
		return true;
	}
</script>


<form:form action="desired-date.html" modelAttribute="tourDate"
	method="post">
	<div class="headerPositionDate">		
			<h3><spring:message code="desiredDate" /></h3>		
	</div>
	<div class="field">
		<label for="n"><spring:message code="start_tour_from"/>:</label>
		<form:input id="dateBeginFrom" type="date" name="calendar"	path="dateBeginFrom"/>
	</div>

	<div class="field">
		<label for="n"><spring:message code="start_tour_before"/>:</label>
		<form:input id="dateBeginBefore" type="date" name="calendar" path="dateBeginBefore"/>
	</div>
	
	<div>
		<form>
			<input type="hidden" name="operation" value="choose">
			<input type="submit" value="<spring:message code="choose"/>" onclick="if (!checkDate()){return false;}"/>
		</form>
	</div>
</form:form>

</html>
