<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Students</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
    <script src="../../javascript/showChangeGroup.js"></script>
</head>
<body>
    <a href="../welcome" name="back">Back</a>
    <div id="tableParent">
        <table id="studentsList" border="1">
            <thead>
            <tr>
                <th>Name</th>
                <th>Family Name</th>
                <th>Group</th>
                <th> </th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <script>
        $(document).ready(function(){
            // get userId from url
            var parameters = window.location.search.substr(1);
            var userId = parameters.substr(parameters.indexOf("userId") + "userId".length + 1);

            $("#studentsList").dataTable({
                ajax:{
                    url: "../Students",
                    type:"GET",
                    data: {userId: userId},
                    dataSrc: ""
                },
                "columnDefs": [
                    {
                        targets: 0,
                        data:"name"
                    },
                    {
                        targets: 1,
                        data: "familyName"
                    },
                    {
                        targets: 2,
                        searchable: false,
                        data: "group.name",
                        render: function(data, type, row){
                            return  data + "<br>" +
                                    "<a href=\"/\" name=\"changeGroup\"" +
                                    " onclick=\"return showChangeGroup(" + userId +
                                    ", " + row.id + ")\">Change group</a>";
                        }
                    },
                    {
                        targets: 3,
                        searchable: false,
                        orderable: false,
                        render: function(data, type, row){
                            return "<a href=\"../DeleteStudent?studentId=" + row.id +
                                "\" name=\"deleteStudent\">Delete</a>";
                        }
                    }
                ]
            });
        })
    </script>
    <div id="changeGroupForm">
    </div>
</body>
</html>