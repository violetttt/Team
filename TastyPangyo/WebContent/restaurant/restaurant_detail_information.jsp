<%@page import="tp.vo.Restaurant"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<h3>${requestScope.selectRes.restaurantName}</h3>
	
				<c:forEach items = "${requestScope.selectRes.images}" var = "image">
					<form action="${initParam.rootPath}/tp/imagedelete" method="post">
						<c:if test=" ${empty initParam.rootPath}/up_images/${image}">
							<img src="${initParam.rootPath}/up_images/${image}" width="400px">
						</c:if>
					</form>
				</c:forEach>
					
	조회수 : ${requestScope.selectRes.hits}<br>
 	번호 : ${requestScope.selectRes.restaurantId}<br>
	카테고리 분류 : ${requestScope.selectRes.foodCategory}<br>
	위치 : ${requestScope.selectRes.location}<p>
	
	소개글 :<input type="text" size = "50px" value = "${requestScope.selectRes.introduction}"/>
	
	<p>
	
	메뉴 : <textarea rows="10" cols="50" readonly>${requestScope.selectRes.menu}</textarea>
	<p>
	
	
	<!-- 리뷰 보여주는 부분!!!!!! -->
	<%
	Restaurant r = (Restaurant)request.getAttribute("selectRes");
	request.setAttribute("reviews", r.getReviews()); %>
	<jsp:include page="/review/list_test.jsp"/>
	
	</center>
	
	 
	
	
	
	
	
</body>
</html>