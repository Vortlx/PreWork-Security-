<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Teachers</title>
</head>
<body>
    <a href="./welcome" name="back">Back</a>
    <form action="./Teachers" method="POST">
        Name: <input name="name" type="text">
        <br>
        Family name: <input name="familyName" type="text">
        <br>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
        <input name="send" type="submit" value="Find">
    </form>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Family Name</th>
            <th>Subjects</th>
            <th> </th>
        </tr>
        <c:forEach items="${teachers}" var="teacher">
            <tr>
                <td>${teacher.name}</td>
                <td>${teacher.familyName}</td>
                <td>${teacher.subject.name}:    ${teacher.subject.type}</td>
                <td>
                    <a href="./DeleteTeacher?teacherId=${teacher.id}" name="deleteTeacher">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>