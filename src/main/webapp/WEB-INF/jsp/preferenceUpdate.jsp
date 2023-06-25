<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Preference Update View</title>
</head>
<body>
	<h1>Preference Update View</h1>
	<form:form  modelAttribute="preference" method="post"
		servletRelativeAction="/mvc/updatePreference">
		<table>
            <tr>
            <td>userId</td>
            <td><form:input path="userId" readonly="true" /></td>
            </tr>
        <tr>
            <td>City</td>
            <td><form:input path="city" readonly="true"/> <form:errors
                    path="city" /></td>
        </tr>
        <tr>
            <td>State</td>
            <td><form:input path="state" readonly="true"/> <form:errors
                    path="state" /></td>
        </tr>
        <tr>
            <td>notifyCycleMins</td>
            <td><form:input path="notifyCycleMins" type="number"/> <form:errors path="notifyCycleMins" />
            </td>
        </tr>
        <tr>
            <td>isSmsActive</td>
            <td><form:input path="isSmsActive" type="text" maxlength="1"/> <form:errors path="isSmsActive" />
            </td>
        </tr>
        <tr>
            <td>isMailActive</td>
            <td><form:input path="isMailActive" type="text" maxlength="1"/> <form:errors path="isMailActive" />
            </td>
        </tr>
        <tr>
            <td>isAppNotifyActive</td>
            <td><form:input path="isAppNotifyActive" type="text" maxlength="1"/> <form:errors path="isAppNotifyActive" />
            </td>
        </tr>
        <tr>
            <td>thresholdInC</td>
            <td><form:input path="thresholdInC" type="number"/> <form:errors path="thresholdInC" />
            </td>
        </tr>
		</table>
		<form:errors>
		</form:errors>
		<form:button name="Update">Update</form:button>
	</form:form>
	<font color="red"> ${message} </font>
</body>
</html>