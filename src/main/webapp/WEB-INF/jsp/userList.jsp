<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List View</title>
</head>
<body>
	<h1>User List View</h1>
	<a href = "<%=request.getContextPath()%>/mvc/createUser">Create</a>
	<br/>
	<br/>
	<table border="1">
		<thead>
			<tr>
				<td align="center">ID</td>
				<td align="center">First Name</td>
				<td align="center">Last Name</td>
				<td align="center" colspan="2">Action</td>
			</tr>
		</thead>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>
					<form action="<%=request.getContextPath()%>/mvc/updateUser/${user.id}" method="get">
						<input type="submit" value="Update">
					</form>
				</td>
				<td>
					<form action="<%=request.getContextPath()%>/mvc/deleteUser/${user.id}" method="get">
						<input type="submit" value="Delete">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<font color="blue"> ${message} </font>
</body>
</html>