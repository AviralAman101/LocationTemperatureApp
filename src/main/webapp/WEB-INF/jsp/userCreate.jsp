<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Create View</title>
</head>
<body>
	<h1>User Create View</h1>
	<form:form modelAttribute="user" method="post"
		servletRelativeAction="/mvc/createUser">
		<table>
			<tr>
				<td>First Name</td>
				<td><form:input path="firstName" />
					</td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><form:input path="lastName" />
					</td>
			</tr>
		</table>
		<form:button name="Create">Create</form:button>
	</form:form>
	<br />
	<font color="red"> ${message} </font>
</body>
</html>