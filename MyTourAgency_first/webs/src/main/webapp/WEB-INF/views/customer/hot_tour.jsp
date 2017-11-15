<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
  
<head>
<style type="text/css">

section {
 display: block;
}

h1 { 
 	position: relative;
  	top: 15px; 	
	color: rgba(255, 255, 255, .9) !important;	
	margin: auto !important; 
	font-size: 3em !important;/* Размер надписи */
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
   	position: relative;
   	top: 60px !important;	
	text-shadow: 1px 1px 2px black, 0 0 1em red !important; /* Параметры тени */
	text-align: center !important; /* Выравнивание по центру */
	color: white !important; /* Белый цвет текста */
	font-size: 6em !important;/* Размер надписи */	
}
</style>
</head>

<section>
	<hr>	
	<h1 style="background-color: #32cd32;" align="center">
		<spring:message code="hot_tour" />
	</h1>
	<hr>	
</section>

<div class = "holiday"><spring:message code="holiday"/></div>

<head>
<style type="text/css">
#secondTable {	
	width: 70% !important;
	height: 50%;
	position: relative;
	left: 10px;
	top: 100px
}


#secondTable td {	
	font-size: 22px;
	font-style: oblique;
	text-align: center;
	vertical-align: middle;
	font-style: oblique;
}

th {
	font-size: 22px;
	background: #ccc; /* Цвет фона */
	border: 1px solid #800; /* Параметры границы */
	padding: 4px; /* Поля в ячейках */
	text-align: center !important; /* Выравнивание по центру */
}

td {
	font-style: oblique;
	min-width: 30px; 
	max-width: 320px;
}

.round-button_off {
	display: block;
	width: 43px;
	height: 43px;
	line-height: 40px;
	border: 3px solid orange;
	border-radius: 50%;
	color: #f5f5f5;
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
.carousel {
  height: 200px !important;
}

.carousel-inner img {
  height: 180px !important;
  margin: auto;
}

.carousel-indicators li {
  border-color: #FFF !important ;
}

.carousel-indicators .active {
  background-color: #87CEEB !important;
}
span {
        color: #F0FFF0 !important;
        
    }
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
</head>


<!-- define the toggle function -->
<script language="javascript">
	function change(item) {
		if (item.value == "round-button_on") {
			item.value = "round-button_off";
		} else {
			item.value = "round-button_on";
		}
	}
</script>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>	

<table id="secondTable" border="2">
	<tr>
		<th><spring:message code="table.id"/></th>		
		<th><spring:message code="table.name" /></th>
		<th><spring:message code="table.country"/></th>
		<th style="width: 250px"><spring:message code="table.dateBegin"/></th>
		<th style="width: 250px"><spring:message code="table.dateEnd"/></th>
		<th><spring:message code="table.numberNights" /></th>
		<th><spring:message code="table.cost"/></th>
	</tr>
	
	<c:forEach var="c" items="${requestScope.allHotTours}">
		<tr>
			<td><c:out value="${c.id_tour}"/></td> 		
    		<c:choose>
		<c:when test="${c.id_tour == '2'}">
    		<td>    		
    		<!-- Wrapper for slides -->
    		<div class="container">
							<div class="row">
								<div class="col-md-3">
									<div id="locations" class="carousel slide" data-ride="carousel" data-interval="false">
									<!-- Indicators -->
										  <ol class="carousel-indicators">
										    <li data-target="#locations" data-slide-to="0" class="active"></li>
										    <li data-target="#locations" data-slide-to="1"></li>
										    <li data-target="#locations" data-slide-to="2"></li>
										    <li data-target="#locations" data-slide-to="3"></li>
										  </ol>
										<div class="carousel-inner">
											<div class="item active">
												<img src="web-content/pic/HolidayTime/01mod.jpg" class="center-block">
											</div>

											<div class="item">
												<img src="web-content/pic/HolidayTime/02mod.jpg" class="center-block">
											</div>

											<div class="item">
												<img src="web-content/pic/HolidayTime/03mod.jpg" class="center-block">
											</div>

											<div class="item">
												<img src="web-content/pic/HolidayTime/04mod.jpg " class="center-block">
											</div>
											<a class="left carousel-control" href="#locations"  data-slide="prev">
											<span class="glyphicon glyphicon-chevron-left"></span>
											</a>
											<a class="right carousel-control" href="#locations"  data-slide="next">
											<span class="glyphicon glyphicon-chevron-right"></span>
											</a>
										</div>
									</div>
								</div>
							</div>
						</div> <c:out value="${c.name}" /></td>
    		</c:when>

				<c:when test="${c.id_tour == '5'}">
					<td>
						<div class="container">
							<div class="row">
								<div class="col-md-3">
									<div id="locations1" class="carousel slide" data-ride="carousel" data-interval="false">
										<!-- Indicators -->
										<ol class="carousel-indicators">
											<li data-target="#locations1" data-slide-to="0" class="active"></li>
											<li data-target="#locations1" data-slide-to="1"></li>
											<li data-target="#locations1" data-slide-to="2"></li>
											<li data-target="#locations1" data-slide-to="3"></li>
										</ol>
										<div class="carousel-inner">
											<div class="item active">
												<img src="web-content/pic/Lagoon/img-1.jpg"
													class="center-block">
											</div>

											<div class="item">
												<img src="web-content/pic/Lagoon/img-2.jpg"
													class="center-block">
											</div>

											<div class="item">
												<img src="web-content/pic/Lagoon/img-3.jpg"
													class="center-block">
											</div>

											<div class="item">
												<img src="web-content/pic/Lagoon/img-4.jpg"
													class="center-block">
											</div>
											<a class="left carousel-control" href="#locations1" data-slide="prev">
												<span class="glyphicon glyphicon-chevron-left"></span>
											</a> 
											<a class="right carousel-control" href="#locations1" data-slide="next">
											 	<span class="glyphicon glyphicon-chevron-right"></span>
											</a>
										</div>
									</div>
								</div>
							</div>
						</div> <c:out value="${c.name}" />
					</td>
				</c:when>
				<c:otherwise>
    		<td><c:out value="${c.name}" /></td>
    		</c:otherwise>
    		</c:choose>  
    		<td><c:out value="${c.country.name}"/></td> 		
			<td><c:out value="${c.dateOfBeginning}" /></td>
			<td><c:out value="${c.dateEnd}" /></td>	
			<td><c:out value="${c.numberOfNights}" /></td>
			<td><c:out value="${c.cost}" /></td>

			<form:form action="reserve-tour.html" modelAttribute="reservation"
				method="post">

				<th><a href="reserve-tour.html?tourId=${c.id_tour}"
					class="round-button_off" onclick="change(this)"><spring:message
							code="choose" /></a></th>
			</form:form>
		</tr>
	</c:forEach>
</table>

<head>
<style type="text/css">
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
<table id="pagination" >
		<tr>
		<%--For displaying Previous link except for the 1st page --%>
		<c:if test="${paginationHotTourInfo.currentPage > 1}">
			<td>
				<a href="hot-tour_previous-page.html"><spring:message code="previous"/></a>
			</td>
		</c:if>

	    <%--For displaying Page numbers. The when condition does not display a link for the current page--%>

        <c:forEach begin="1" end="${paginationHotTourInfo.noOfPages}" var="i">
            <c:choose>
                <c:when test="${paginationHotTourInfo.currentPage == i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="hot-tour_go-to-page.html?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>

		<%--For displaying Next link --%>
		<c:if test="${paginationHotTourInfo.currentPage < paginationHotTourInfo.noOfPages}">
		    <td>
		    	<a href="hot-tour_next-page.html"><spring:message code="next"/></a>
		    </td>
		</c:if>
	</tr>
</table> 


<head>
<style>
section {
 display: block;
}
.return {
	position: relative;
	text-align: left;
	top: 150px
}
.return a{
	font-family: arial!important;
	font-style: oblique!important;
	font-weight: bold!important;
	font-size: 1.2em!important;
	padding: 10px!important;
	color:  #A0522D!important;
	
}
</style>
</head>
<section>
<div class="return">
	[<a href="customer.html"><spring:message code="backPage" /></a>]
</div>
</section>