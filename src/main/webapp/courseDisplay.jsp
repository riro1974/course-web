<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>courseDisplay</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:useBean id="course" class="com.example.course.entity.Course"
	scope="request"></jsp:useBean>
<form action="CourseControllerServlet">
<table border="1">
	<tbody>
		<tr>
			<td>Course Code: </td>
			<td><input type="text" name="code" 
size="5" maxlength="5"
				value="<%= course.getCode() %>"></td>
		</tr>
		<tr>
			<td>Description: </td>
			<td><input type="text" 
name="description" size="40"
				value="<%= course.getDescription() %>"></td>
		</tr>
		<tr>
			<td>Duration: </td>
			<td><input type="text" name="duration" 
size="4" maxlength="4"
				value="<%= course.getDuration() %>"></td>
		</tr>
		<tr>
			<td>Fee: </td>
			<td><input type="text" name="fee" 
size="4" maxlength="4"
				value="<%= course.getFee() %>"></td>
		</tr>
	</tbody>
</table>
<table border="1">
	<tbody>
		<tr>
			<td><input type="submit" 
name="create" value="Create">
			</td>
			<td><input type="submit" 
name="read" value="Read">
			</td>
			<td><input type="submit" 
name="update" value="Update">
			</td>
			<td><input type="submit" 
name="delete" value="Delete">
			</td>
		</tr>
	</tbody>
</table>
</form>
<%= request.getAttribute("message") %>


</body>
</html>