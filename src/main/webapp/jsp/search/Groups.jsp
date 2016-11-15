<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Groups</title>
</head>
<body>
    <a href="../welcome" name="back">Back</a>
    <table border="1">
        <tr>
            <th>Name</th>
            <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
                <th> </th>
            </sec:authorize>
        </tr>
            <c:forEach items="${groups}" var="group">
                <tr>
                    <td>
                        <a href="./MyGroup?groupId=${group.id}&userId=${param.userId}" name="toGroups">${group.name}</a>
                    </td>
                    <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
                        <td>
                            <a href="../delete/DeleteGroup" name="deleteGroup">Delete</a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
            <tr>
                <td colspan="2">
                    <a href="../add/AddGroup.jsp" name="addGroup">Add</a>
                </td>
            </tr>
        </sec:authorize>
    </table>
</body>
</html>
