<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Teachers</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
    <script src="../javascript/personValidation.js"></script>
    <style>
        .error{
            color: RED;
        }
    </style>
</head>
<body>
    <a href="welcome" name="back">Back</a>
    <form action="Teachers" method="POST" onsubmit="return personIncorrectNameValidation()">
        Name: <input name="name" id="personName" type="text">
        <span class="error" id="incorrName"></span>
        <br>
        Family name: <input name="familyName" id="personFamilyName" type="text">
        <span class="error" id="incorrFamilyName"></span>
        <br>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
        <input name="send" type="submit" value="Find">
    </form>
    <div id="tableParent">
        <table id="teachersList" border="1">
            <thead>
            <tr>
                <th>Name</th>
                <th>Family Name</th>
                <th>Subjects</th>
                <th> </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${teachers}" var="teacher">
                <tr>
                    <td>${teacher.name}</td>
                    <td>${teacher.familyName}</td>
                    <td>${teacher.subject.name}:    ${teacher.subject.type}</td>
                    <td>
                        <a href="DeleteTeacher?teacherId=${teacher.id}" name="deleteTeacher">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <script>
        $(document).ready(function(){
            $("#teachersList").dataTable({
                "columnDefs": [{
                    "targets": 3,
                    "searchable": false,
                    "orderable": false
                }]
            });
        })
    </script>
</body>
</html>