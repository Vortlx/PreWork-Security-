<%--
  Created by IntelliJ IDEA.
  User: lebedevas
  Date: 28.12.2016
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>MyGroup</title>

    <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>
    <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>

    <script src="../staticresources/javascript/showChangeGroup.js"></script>
    <script src="../staticresources/javascript/getUrlParameters.js"></script>

</head>
<body>
    <div class="container upMargin">
        <div  class="row">
            <div class="col-sm-8">
                <div class="link">
                    <a href="#" name="Back" class="btn btn-default" onclick="return hideInfo()">Hide</a>
                </div>
                <table id="groupInfo" class="table-bordered">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Family Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div id="specifyGroup">
            </div>
        </div>
        <div class="row">
            <div class="btn-toolbar">
                <div class="btn-group">
                    <div class="col-sm-1">
                        <a href="#" class="btn btn-default" onclick="return nextTablePage(${param.groupId} ${param.userId},${param.page - 1})">&lt;</a>
                    </div>
                </div>
                <div class="btn-group">
                    <a href="#" class="btn btn-default">...</a>
                </div>
                <div class="btn-group">
                    <div class="col-sm-1">
                        <a href="#" class="btn btn-default" onclick="return nextTablePage(${param.groupId}, ${param.userId}, ${param.page + 1})">&gt;</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(
        		nextTablePage(${param.userId}, ${param.page})
        );

        function nextTablePage(userId, page){
            $("#groupInfo").dataTable({
                ajax:{
                    url: "MyGroup",
                    type: "GET",
                    data: {
                        groupId: null,
                        userId: userId,
                        page: page
                    },
                    dataSrc: "students"
                },
                bFilter : false,
                bLengthChange: false,
                paging: false,
                info: false,
                serverSide: true,
                columns:[
                    {data: "name"},
                    {data: "familyName"}
                ]
            });
            return false;
        };
    </script>
</body>
</html>