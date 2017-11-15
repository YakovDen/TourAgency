<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>

  <head>
<script type="text/javascript">
  var total_pics_num = 4; // колличество изображений
  var interval = 5000;    // задержка между изображениями
  var time_out = 1;       // задержка смены изображений
  var i = 0;
  var timeout;
  var opacity = 100;
  function fade_to_next() {
    opacity--;
    var k = i + 1;
    var image_now = 'image_' + i;
    if (i == total_pics_num) k = 1;
    var image_next = 'image_' + k;
    document.getElementById(image_now).style.opacity = opacity/100;
    document.getElementById(image_now).style.filter = 'alpha(opacity='+ opacity +')';
    document.getElementById(image_next).style.opacity = (100-opacity)/100;
    document.getElementById(image_next).style.filter = 'alpha(opacity='+ (100-opacity) +')';
    timeout = setTimeout("fade_to_next()",time_out);
    if (opacity==1) {
      opacity = 100;
      clearTimeout(timeout);
    }
  }
  setInterval (
    function() {
      i++;
      if (i > total_pics_num) i=1;
      fade_to_next();
    }, interval
  );
</script>

<style type="text/css">

</style>
</head>
<body>
<div class="banner">
  <img src='web-content/pic/banner/images01.jpg' id="image_1" style="position: absolute;margin:0 0 0 850px" />
  <img src='web-content/pic/banner/images02.jpg' id="image_2" style="opacity: 0; filter: alpha(opacity=0); position: absolute;margin:0 0 0 850px" />
  <img src='web-content/pic/banner/images03.jpg' id="image_3" style="opacity: 0; filter: alpha(opacity=0); position: absolute;margin:0 0 0 850px" />
  <img src='web-content/pic/banner/images04.jpg' id="image_4" style="opacity: 0; filter: alpha(opacity=0); position: absolute;margin:0 0 0 850px" />
  <img src='web-content/pic/banner/images05.jpg' id="image_5" style="opacity: 0; filter: alpha(opacity=0); position: absolute;margin:0 0 0 850px" />
  <img src='web-content/pic/banner/images06.jpg' id="image_6" style="opacity: 0; filter: alpha(opacity=0); position: absolute;margin:0 0 0 850px" />
  <img src='web-content/pic/banner/images07.jpg' id="image_7" style="opacity: 0; filter: alpha(opacity=0); position: absolute;margin:0 0 0 850px" />  
</div>
</body>

<head>
<style>
section {
 display: block;
}

div a{	
	padding: 1px;
	font-weight: bolder !important;
	font-size: 20px !important;
	font-family: calibri !important;
	color: #800080 !important; 	
	margin: 0 0 0 0 !important;
            
}
</style>
</head>
<section>
 <div style="text-align: right;"> <a href="?language=en"><b>[EN]</b></a>	|	<a href="?language=ru"><b>[RU]</b></a></div>
 
 <div style="text-align: left;"> 
 	
 		<div> 
  	 		[<a href="logout.html"><spring:message code="logout"/></a>] 
 		</div>
 </div>
 </section>
 