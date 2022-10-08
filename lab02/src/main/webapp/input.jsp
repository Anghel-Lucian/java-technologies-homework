<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.carapp.model.CarBean" %>
<!DOCTYPE html>
<!-- initialize CarBean and access the car categories, create an attribute to be read inside the JSP -->
<%! CarBean cars = new CarBean(); %>
<% ArrayList<String> cat = cars.getCategories(); %>
<% request.setAttribute("cat", cat); %>
<html>
<head>
<meta charset="UTF-8">
<title>Input</title>
</head>
<body>
	<div align="center">
		<h1>Add a car</h1>
		<!-- the value for "action" attribute is the path to the servlet. At the end of the "/car" servlet's "doPost" method, user will be redirected to "result.jsp" -->
		<form action="<%= request.getContextPath() %>/car" method="post">
			<div>
				Category:
		        <select name="category">
	                <option value="">--</option>
	                <c:forEach var="category" items="${cat}">
                        <option value="${category}">${category}</option>
	                </c:forEach>
		        </select>
			</div>
			<div>
		        Make:
		        <input name="make" type="text" />
			</div>
			<div>
		        Year:
		        <input name="year" type="number" />
			</div>
			
			<button type="submit">Submit</button>
		</form>
	</div>
</body>
</html>