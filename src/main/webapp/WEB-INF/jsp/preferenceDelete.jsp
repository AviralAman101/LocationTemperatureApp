<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Preference Delete View</title>
</head>
<body>
	<h1>Preference Delete View</h1>
	<form:form modelAttribute="preference" method="post"
		servletRelativeAction="/mvc/deletePreference">
		<table>
			 <tr>
                        <td>userId</td>
                        <td><form:input path="userId" readonly="true" /></td>
                        </tr>
                    <tr>
                        <td>City</td>
                        <td><form:input path="city" readonly="true"/>
                    </tr>
                    <tr>
                        <td>State</td>
                        <td><form:input path="state" readonly="true"/>
                    </tr>
                    <tr>
                        <td>notifyCycleMins</td>
                        <td><form:input path="notifyCycleMins" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>isSmsActive</td>
                        <td><form:input path="isSmsActive" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>isMailActive</td>
                        <td><form:input path="isMailActive" readonly="true" />
                        </td>
                    </tr>
                    <tr>
                        <td>isAppNotifyActive</td>
                        <td><form:input path="isAppNotifyActive" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>thresholdInC</td>
                        <td><form:input path="thresholdInC" readonly="true"/>
                        </td>
                    </tr>
		</table>
		<form:button name="Delete">Delete</form:button>
	</form:form>
	<font color="red"> ${message} </font>
</body>
</html>