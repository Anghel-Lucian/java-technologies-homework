<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.carapp.model.CarBean" %>
<%@ page import="com.carapp.model.Car" %>
<!DOCTYPE html>
<!-- initialize CarBean and access the cars, create an attribute to be read inside the JSP -->
<%! CarBean carBean = new CarBean(); %>
<% ArrayList<Car> cars = carBean.getCars(); %>
<% request.setAttribute("cars", cars); %>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>
	<div align="center">
		<h1>Car Repository</h1>
		<table border="1">
			<thead>
				<tr>
					<th>Category</th>
					<th>Make</th>
					<th>Year</th>
				</tr>
			</thead>
			<tbody>
		        <c:forEach var="car" items="${cars}">
	                <tr>
	                   <!-- call the getters for each Car instance, render table columns -->
	                   <td>${car.getCategory()}</td>
					   <td>${car.getMake()}</td>
					   <td>${car.getYear()}</td>
	                </tr>
		        </c:forEach>
	        </tbody>
		</table>
	</div>
</body>
</html>