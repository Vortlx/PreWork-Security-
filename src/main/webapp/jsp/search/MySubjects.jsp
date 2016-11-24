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
    <sec:authorize access="hasRole('ROLE_STUDENT')">
        <a href="welcome" name="Back">Back</a>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
        <a href="Groups?userId=${userId}" name="Back">Back</a>
    </sec:authorize>
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
                   ${subject.teacher.name} ${subject.teacher.familyName}
                </td>
                <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
                    <td>
                        <a href="DeleteSubject?groupId=${groupId}&subjectId=${subject.id}&userId=${userId}"
                           name="deleteSubject">Delete</a>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
        <sec:authorize  access="hasRole('ROLE_DEPARTMENT')">
            <tr>
                <td colspan="3">
                    <a href="AddSubjectPage?groupId=${groupId}&userId=${userId}" name="addSubject">Add</a>
                </td>
            </tr>
        </sec:authorize>
    </table>
</body>
</html>