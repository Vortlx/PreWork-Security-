<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>My Subjects</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
    <script src="../javascript/showSubjects.js"></script>
</head>
<body>
    <div id="tableParent" style="margin-bottom: 50px">
        <sec:authorize access="hasRole('ROLE_STUDENT')">
            <a href="/" name="Back" onclick="return hideInfo()">Hide</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
            <a href="Groups?userId=${userId}" name="Back">Back</a>
        </sec:authorize>
        <table id="subjectList" border="1">
            <thead>
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
            </thead>
            <tbody>
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
                            <a href="/" name="addSubject"
                               onclick="return showSubjects(${groupId}, ${userId})">Add</a>
                        </td>
                    </tr>
                </sec:authorize>
            </tbody>
        </table>
        <script>
            $(document).ready(function(){
                $("#subjectList").dataTable();
            })
        </script>
    </div>
    <div id="newSubject">
    </div>
</body>
</html>