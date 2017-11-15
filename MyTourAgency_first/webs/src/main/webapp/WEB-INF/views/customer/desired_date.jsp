<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>

<html>
<head>
<style>
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
	text-shadow: 1px 1px 2px black, 0 0 1em red !important;
	/* Параметры тени */
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

<!--
-->
h3 {
	position: relative;
	top: -10px;
	left: 50px;
	font-family: annabelle, cursive;
	font-weight: bold;
	font-size: 1.5em;
	text-decoration: underline;
	padding: 10px;
	color: #d2691e;
	text-shadow: 1px 1px 0 rgba(0, 0, 0, .3);
}

.round-button_off {
	display: block;
	width: 43px;
	height: 43px;
	line-height: 40px;
	border: 3px solid orange;
	border-radius: 50%;
	color: #f5f5f5;
	/*   color:red; */
	text-align: center;
	text-decoration: none;
	background: #464646;
	box-shadow: 0 0 3px gray;
	font-size: 10px;
	font-weight: italics;
}

.round-button_off:hover {
	background: pink;
}

.round-button_on {
	display: block;
	width: 43px;
	height: 43px;
	line-height: 40px;
	border: 3px solid orange;
	border-radius: 50%;
	/*  color:#f5f5f5; */
	color: #900;
	font-weight: bold;
	text-align: center;
	text-decoration: none;
	background: black;
	box-shadow: 0 0 3px gray;
	font-size: 10px;
	font-weight: italics;
}

.round-button_off:active {
	background-color: #3e8e41;
	box-shadow: 0 5px #666;
	transform: translateY(4px);
}

<!--
-->
section {
	display: block;
}

.return {
	position: relative;
	text-align: left;
	top: 150px
}

.return a {
	font-family: arial !important;
	font-style: oblique !important;
	font-weight: bold !important;
	font-size: 1.2em !important;
	padding: 10px !important;
	color: #A0522D !important;
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

<body>
<hr>
<h1>
	<spring:message code="userTitle" />
</h1>
<hr>

<div class = "holiday"><spring:message code="holiday"/></div>

<h3>
	<spring:message code="desiredTours" />	
</h3>

<table>
	<tr>
		<th><spring:message code="table.id"/></th>
		<th><spring:message code="table.name"/></th>
		<th><spring:message code="table.dateBegin"/></th>
		<th><spring:message code="table.dateEnd"/></th>
		<th><spring:message code="table.numberNights"/></th>
		<th><spring:message code="table.cost"/></th>
		<th><spring:message code="table.typeTour"/></th>
		<th><spring:message code="table.kindTour"/></th>        		
	</tr>        	
	<c:forEach var="p" items="${requestScope.allDesiredTours}"> 
		<tr>
			<td><c:out value="${p.id_tour}"/></td>           		
			<td><c:out value="${p.name}" /></td>
			<td><c:out value="${p.dateOfBeginning}" /></td>
			<td><c:out value="${p.dateEnd}" /></td>					
			<td><c:out value="${p.numberOfNights}" /></td>
			<td><c:out value="${p.cost}" /></td>
			<td><c:out value="${p.typeOfTour.typeOfTour}"/></td>	
			<td><c:out value="${p.kindOfTour.kindOfTour}"/></td>

			<form:form action="desired-date.html" modelAttribute="tourDate"	method="post">			
				
				<%-- <th>				 			
 					<a href="desired-date.html?tourId=${p.id_tour}"
								class="round-button_off" onclick="change(this)" ><spring:message code="choose"/></a>				
 				</th> --%>
 				<th>				 			
 					<a href="reserve-tour.html?tourId=${p.id_tour}"
								class="round-button_off" onclick="change(this)" ><spring:message code="choose"/></a>				
 				</th>
			</form:form>
		<tr/>
	</c:forEach>
</table>

<br>  

<head>
<style>
#pagination {
	position: relative;
	height: 50% !important;
	left: 10px;
	top: 130px;
}

#pagination {
	font-family: Times New Roman, serif;
	text-align: center;
	text-decoration: none;
	box-shadow: 0 0 3px gray;
	font-size: 20px;
	font-weight: italics;
	border-spacing: 0 10px !important;
}

#pagination a {
	color: #4B0082 !important;
}

tr, td {
	border: 1px solid maroon; /* Параметры рамки */
	padding: 4px;
}
</style>
</head>
<table id="pagination">
	<tr>
		<%--For displaying Previous link except for the 1st page --%>
		<c:if test="${paginationDesiredTourInfo.currentPage > 1}">
			<td>
				<a href="desired-date_previous-page.html"><spring:message code="previous"/></a>
			</td>
		</c:if>

	    <%--For displaying Page numbers. The when condition does not display a link for the current page--%>

        <c:forEach begin="1" end="${paginationDesiredTourInfo.noOfPages}" var="i">
            <c:choose>
                <c:when test="${paginationDesiredTourInfo.currentPage == i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="desired-date_go-to-page.html?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>

		<%--For displaying Next link --%>
		<c:if test="${paginationDesiredTourInfo.currentPage < paginationDesiredTourInfo.noOfPages}">
		    <td>
		    	<a href="desired-date_next-page.html"><spring:message code="next"/></a>
		    </td>
		</c:if>
	</tr>
</table> 
<br /><br />

<section>
<div class="return">
	[<a href="customer.html"><spring:message code="backPage" /></a>]
</div>
</section>
</body>
</html>