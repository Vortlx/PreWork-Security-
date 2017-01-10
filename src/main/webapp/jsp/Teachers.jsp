<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp">
    <jsp:param name="userId" value="${param.userId}"/>
</jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Teachers</title>

    <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>
    <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>

    <script src="../staticresources/javascript/getUrlParameters.js"></script>

</head>
<body>
<div id="header"></div>
<div class="container upMargin">
    <div class="row">
        <div class="col-sm-11">
            <div id="tableParent">
                <table id="teachersList" class="table-bordered">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Family Name</th>
                        <th>Subjects</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="row paging">
        <div class="btn-toolbar">
            <div id="prevPage" class="btn-group">
                <div id="pageLeft" class=" col-sm-1">
                </div>
            </div>
            <div id="pageButtons" class="btn-group">
            </div>
            <div id="nextPage" class="btn-group">
                <div id="pageRight" class="col-sm-1">
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    // Function return object with url parameters
    var pars = getUrlParameters(window.location);

    $(document).ready(function () {
        drawTable(pars["userId"], 1);
    });

    function drawTable(userId, page) {
        $("#teachersList").dataTable({
            ajax: {
                url: "Teachers",
                type: "GET",
                data: {
                    userId: userId,
                    page: page
                },
                dataSrc: "teachers"
            },
            destroy: true,
            bFilter: false,
            bLengthChange: false,
            processing: false,
            paging: false,
            info: false,
            serverSide: true,
            columnDefs: [
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
                    data: function (row) {
                        return row.subject.name + ": " + row.subject.type;
                    }
                },
                {
                    targets: 3,
                    searchable: false,
                    orderable: false,
                    render: function (data, type, row) {
                        if (page == undefined) {
                            return "<a href=\"DeleteTeacher?teacherId=" + row.id +
                                "&userId=" + userId + "\" name=\"deleteTeacher\">Delete</a>";
                        } else {
                            return "<a href=\"DeleteTeacher?teacherId=" + row.id +
                                "&userId=" + userId + "&page=" + page + "\" name=\"deleteTeacher\">Delete</a>";
                        }

                    }
                }
            ],
            fnInitComplete: function (setting, json) {
                if (json.page > 1) {
                    $("#pageLeft").html("<a href=\"#\" class=\"btn btn-default\" onclick=\"return drawTable(" + userId + ", " + (page - 1) + ")\">&lt;</a>");
                }else{
                    $("#pageLeft").html("");
                }

                if (json.page < json.maxPage) {
                    $("#pageRight").html("<a href=\"#\" class=\"btn btn-default\" onclick=\"return drawTable(" + userId + ", " + (page + 1) + ")\">&gt;</a>");
                } else{
                    $("#pageRight").html("");
                }

                $("#pageButtons").html(function () {
                    var buttons = "";

                    for (var i = 1; i <= json.maxPage; i++) {
                        if (i == json.page) {
                            buttons += "<a href=\"#\" class=\"btn btn-default active\" onclick=\"return drawTable(" + userId + ", " + i + ")\">" + i + "</a>"
                        } else {
                            buttons += "<a href=\"#\" class=\"btn btn-default\" onclick=\"return drawTable(" + userId + ", " + i + ")\">" + i + "</a>";
                        }
                    }

                    return buttons;
                })
            }
        });

        return false;
    };
</script>
</body>
</html>