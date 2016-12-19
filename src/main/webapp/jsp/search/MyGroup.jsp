<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>My Group</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
</head>
<body>
    <sec:authorize access="hasRole('ROLE_STUDENT')">
        <a href="/" name="Back" onclick="return hideInfo()">Hide</a>
    </sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_DEPARTMENT')">
        <a href="/" name="Back" onclick="return hideInfo()">Hide</a>
    </sec:authorize>

    <table id="groupList" border="1">
        <thead>
            <tr>
                <th colspan="2">${group.name}</th>
            </tr>
            <tr>
                <th>Name</th>
                <th>Family Name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${group.students}" var="student">
                <tr>
                    <td>${student.name}</td>
                    <td>${student.familyName}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <script>
        $(function(){
            $('#groupList').dataTable();
        });
    </script>
</body>
</html>