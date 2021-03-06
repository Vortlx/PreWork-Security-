<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp">
    <jsp:param name="userId" value="${param.userId}"/>
</jsp:include>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Groups</title>

    <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>
    <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>

    <script src="../staticresources/javascript/showGroup.js"></script>
    <script src="../staticresources/javascript/getUrlParameters.js"></script>
</head>
<body>
<div id="header"></div>
<div id="tableParent" class="container upMargin">
    <div class="row">
        <div class="col-sm-6">
            <table id="tableGroups" class="table-bordered">
                <thead>
                <tr>
                    <th>Name</th>
                    <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
                        <th></th>
                        <th></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <div class="col-sm-6">
            <div id="specifyGroup">
            </div>
        </div>
    </div>
    <div class="row paging">
        <div class="btn-toolbar">
            <div class="btn-group">
                <div id="pageLeft" class="col-sm-1">
                </div>
            </div>
            <div id="pageButtons" class="btn-group">
            </div>
            <div class="btn-group">
                <div id="pageRight" class="col-sm-1">
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var pars = getUrlParameters(window.location);
    $(document).ready(function () {
        drawTable(pars["userId"], 1);
    });

    function drawTable(userId, page) {
        $("#tableGroups").dataTable({
            ajax: {
                url: "Groups",
                type: "GET",
                data: {
                    userId: userId,
                    page: page
                },
                dataSrc: "groups"
            },
            destroy: true,
            bFilter: false,
            bLengthChange: false,
            paging: false,
            info: false,
            serverSide: true,
            columnDefs: [
                {
                    targets: 0,
                    data: function (row) {
                        return "<a href=\"#\" name=\"toGroups\"" +
                            "onclick=\"return showGroup(" +
                            row.id + ", " + userId +
                            ", 1)\">" + row.name + "</a>";
                    }
                },
                {
                    targets: 1,
                    data: null,
                    render: function (row) {
                        return "<a href=\"MySubjects?userId=" +
                            userId + "&groupId=" + row.id +
                            "\" name=\"subjects\">Subjects</a>";
                    }
                },
                {
                    targets: 2,
                    data: null,
                    render: function (row) {
                        if (page == undefined) {
                            return "<a href=\"DeleteGroup?userId=" +
                                userId + "&groupId=" + row.id +
                                "\" name=\"deleteGroup\">Delete</a>";
                        } else {
                            return "<a href=\"DeleteGroup?userId=" +
                                userId + "&groupId=" +
                                row.id + "&page=" + page +
                                "\" name=\"deleteGroup\">Delete</a>";
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
                            buttons += "<a href=\"#\" class=\"btn btn-default active\" onclick=\"return drawTable(" +
                                userId + ", " + i + ")\">" + i + "</a>"
                        } else {
                            buttons += "<a href=\"#\" class=\"btn btn-default\" onclick=\"return drawTable(" +
                                userId + ", " + i + ")\">" + i + "</a>"
                        }
                    }
                    return buttons;
                })
            }
        });

        return false;
    }
    ;
</script>
</body>
</html>