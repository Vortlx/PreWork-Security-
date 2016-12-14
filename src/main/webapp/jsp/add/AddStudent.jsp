<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Add Group</title>
</head>
<body>
    <a href="../welcome" name="back">Back</a>
    <form action="AddStudent" method="POST">
        Name: <input name="name" type="text" required>
        <br>
        Family Name: <input name="familyName" type="text" required>
        <br>
        <select name="groupID">
            <c:forEach items="${groups}" var="group">
                <option value="${group.id}">
                    ${group.name}
                </option>
            </c:forEach>
        </select>
        <br>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
        <input name="add" type="submit" value="Add">
    </form>
    <br>
    ${message}
</body>
</html>