<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>

<style type="text/css">

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
	font-size: 22px;		 
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
</style>

<u style="color: #d2691e">
	<spring:message code="toursBooked" />
</u>
<br><br>

<table>
		<tr>
			<th><spring:message code="table.id"/></th>         		       		
			<th><spring:message code="table.name"/></th>
			<th><spring:message code="table.cost"/></th>
			<th><spring:message code="table.tourPaid"/></th>        		     		
		</tr> 

		<c:set var="limit" value="${0}"/>               	
		<c:forEach var="n" items="${requestScope.allToursForClient}">         	
			<tr>
				<c:if test="${n.idUT gt 0}">           				 
         			<c:set var="limit" value="${limit+1}"/>
    			</c:if>

				<td><c:out value="${limit}"/>
				<%-- <td align="center"><c:out value="${n.idUT}"/>  --%>           					
				<td><c:out value="${n.tour.name}" /></td>					
				<td><c:out value="${n.tour.cost}" /></td>																
				<td><c:out value="${n.isPaid()}" /></td>	
					
					<td>
					<c:if test="${n.isPaid()}">
                    <a href="un-pay-tour.html?userTourId=${n.getIdUT()}">UnPay</a>
                    </c:if>
                    <c:if test="${!n.isPaid()}">
					<a href="pay-tour.html?userTourId=${n.getIdUT()}">Pay</a>
                    </c:if>
					 </td>								           		        		            		
        		<tr/>
        	</c:forEach>       	
        </table>
        <br />
		<br />
        <div> [<a href="customer.html"><spring:message code="backPage"/></a>]</div>	