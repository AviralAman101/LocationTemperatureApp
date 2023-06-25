<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Preference Create View</title>
</head>
<body>
	<h1>Preference Create View</h1>
	<form:form modelAttribute="preference" method="post"
		servletRelativeAction="/mvc/createPreference">
		<table>
			<tr>
				<td>Current City Name</td>
				<td><form:input path="city"/>
					</td>
			</tr>
			<tr>
				<td>Current State Name</td>
				<td><form:input path="state"/>
					</td>
			</tr>
			<tr>
				<td>User Id</td>
				<td><form:input path="userId"/>
					</td>
			</tr>
            <tr>
                <td>Notify In Mins</td>
                <td><form:input path="notifyCycleMins" type="number" />
                    </td>
            </tr>
            <tr>
                <td>Send SMS</td>
                <td><form:input path="isSmsActive" maxlength="1" type="text" />
                    </td>
            </tr>
			<tr>
				<td>Send Mail</td>
				<td><form:input path="isMailActive" maxlength="1" type="text" />
					</td>
			</tr>
			<tr>
				<td>Application Notify</td>
				<td><form:input path="isAppNotifyActive" maxlength="1" type="text"  />
					</td>
			</tr>
			<tr>
				<td>Thershold in Celsius</td>
				<td><form:input path="thresholdInC" type="number"/>
					</td>
			</tr>
		</table>
		<form:button name="Create">Create</form:button>
	</form:form>
	<br />
	<font color="red"> ${message} </font>
</body>
</html>