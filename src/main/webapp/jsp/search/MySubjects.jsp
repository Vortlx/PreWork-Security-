<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>My Subjects</title>
</head>
<body>
    <a href="./welcome" name="Back">Back</a>
    <table border="1">
        <tr>
            <th colspan="4">Subjects</th>
        </tr>
        <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Teacher</th>
            <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
                <th> </th>
            </sec:authorize>
        </tr>
        <c:forEach items="${subjects}" var="subject">
            <tr>
                <td>${subject.name}</td>
                <td>${subject.type}</td>
                <td>
                    <c:forEach items="${subject.teachers}" var="teacher">
                        ${teacher.name} ${teacher.familyName}
                    </c:forEach>
                </td>
                <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
                    <a href="./" name="deleteSubject">Delete</a>
                </sec:authorize>
            </tr>
        </c:forEach>
        <sec:authorize  access="hasRole('ROLE_DEPARTMENT')">
            <tr colspan="4">
                <a href="./" name="addSubject">Add</a>
            </tr>
        </sec:authorize>
    </table>
</body>
</html>