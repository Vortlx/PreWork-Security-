<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Add Subject</title>
</head>
<body>
    <a href="MySubjects?groupId=${groupId}&userId=${userId}" name="back">Back</a>

    <form action="AddSubject" method="POST">
        Subject: <select name="subjectName">
            <c:forEach items="${subjects}" var="subject">
                <option value="${subject.name}">${subject.name}</option>
            </c:forEach>
        </select>

        <select name="subjectType">
            <option value="LECTURE">Lecture</option>
            <option value="PRACTICE">Practice</option>
        </select>

        <br>

        <input name="groupId" type="hidden" value="${groupId}"/>
        <input name="userId" type="hidden" value="${userId}"/>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
        <input name="add" type="submit" value="Add">
    </form>
    <br>
    ${message}
</body>
</html>