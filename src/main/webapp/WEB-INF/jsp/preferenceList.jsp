<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Location List View</title>
</head>
<body>
	<h1>Location List View</h1>
	<a href = "<%=request.getContextPath()%>/mvc/createPreference">Create</a>
	<br/>
	<br/>
	<table border="1">
		<thead>
			<tr>
				<td align="center">Location City</td>
				<td align="center">Location State</td>
				<td align="center">User ID</td>
				<td align="center">Notify Miniutes</td>
				<td align="center">Send SMS</td>
				<td align="center">Send Mail</td>
				<td align="center">Application Notify</td>
				<td align="center">Threshold in Celsius</td>
				<td align="center" colspan="2">Action</td>
			</tr>
		</thead>
		<c:forEach items="${preferences}" var="pref">
			<tr>
				<td>${pref.city}</td>
				<td>${pref.state}</td>
				<td>${pref.userId}</td>
				<td>${pref.notifyCycleMins}</td>
				<td>${pref.isSmsActive}</td>
				<td>${pref.isMailActive}</td>
				<td>${pref.isAppNotifyActive}</td>
				<td>${pref.thresholdInC}</td>
				<td>
					<form action="<%=request.getContextPath()%>/mvc/updatePreference/${pref.userId}/${pref.city}/${pref.state}" method="get">
						<input type="submit" value="Update">
					</form>
				</td>
				<td>
					<form action="<%=request.getContextPath()%>/mvc/deletePreference/${pref.userId}/${pref.city}/${pref.state}" method="get">
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