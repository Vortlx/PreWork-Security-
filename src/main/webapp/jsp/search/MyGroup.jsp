<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>My Group</title>
</head>
<body>
    <a href="../welcome" name="Back">Back</a>
    <table border="1">
        <tr>
            <th colspan="3">${group.name}</th>
        </tr>
        <tr>
            <th>Name</th>
            <th>Family Name</th>
            <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
                <th>Delete</th>
            </sec:authorize>
        </tr>
        <c:forEach items="${group.students}" var="student">
            <tr>
                <td>${student.name}</td>
                <td>${student.familyName}</td>
                <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
                    <a href="../delete/DeleteStudent?studentId=${student.id}" name="deleteStudent">Delete</a>
                </sec:authorize>
            </tr>
        </c:forEach>
        <sec:authorize  access="hasRole('ROLE_DEPARTMENT')">
            <tr colspan="3">
                <a href="../add/AddStudent.jsp?groupId=${group.id}" name="addStudent">Add</a>
            </tr>
        </sec:authorize>
    </table>
</body>
</html>