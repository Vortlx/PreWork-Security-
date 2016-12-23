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
</head>
<body>
    <a href="../welcome" name="back">Back</a>
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
            // get userId from url
            // +2 in formula because exist characters "?" and "="
            var userId = window.location.search.substr("userId".length + 2);

            $("#teachersList").dataTable({
                ajax:{
                    url: "../Teachers",
                    type: "GET",
                    data: {userId: userId},
                    dataSrc: ""
                },
                "columnDefs": [
                    {
                        targets: 0,
                        data: "name"
                    },
                    {
                        targets: 1,
                        data: "familyName"
                    },
                    {
                        targets: 2,
                        data:function(row){
                            return row.subject.name + ": " + row.subject.type;
                        }
                    },
                    {
                        targets: 3,
                        searchable: false,
                        orderable: false,
                        render: function(data, type, row){
                            return "<a href=\"DeleteTeacher?teacherId=" + row.id +
                                "\" name=\"deleteTeacher\">Delete</a>";
                        }
                    }
                ]
            });
        })
    </script>
</body>
</html>