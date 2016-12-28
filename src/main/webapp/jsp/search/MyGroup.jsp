<%--
  Created by IntelliJ IDEA.
  User: lebedevas
  Date: 28.12.2016
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyGroup</title>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../staticresources/css/formStyle.css"/>
    <link rel="stylesheet" type="text/css" href="../../staticresources/css/marginForButtons.css"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
    <script src="../staticresources/javascript/showChangeGroup.js"></script>
    <script src="../staticresources/javascript/getUrlParameters.js"></script>
</head>
<body>
    <div class="container upMargin">
        <div  class="row">
            <div class="col-sm-8">
                <div class=\"link\">
                    <a href="#" name="Back" class="btn btn-default" onclick="return hideInfo()">Hide</a>
                </div>
                <table id="groupInfo" class="table-bordered">
                    <thead>
                    <tr>
                        <th colspan="2">${group.name}</th>
                    </tr>
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
        var params = getUrlParameters(window.location);
        $(document).ready(nextTablePage(params["groupId"], params["userId"], params["page"]));

        function nextTablePage(groupId, userId, page){
            $("#specifyGroup").dataTable({
                ajax:{
                    url: "MyGroup",
                    type: "GET",
                    data: {
                        groupId: groupId,
                        userId: userId,
                        page: page
                    },
                    dataSrc: ""
                },
                destroy: true,
                bFilter : false,
                bLengthChange: false,
                paging: false,
                info: false,
                columnsDef:[
                    {data: "name"},
                    {data: "familyName"}
                ]
            });
            return false;
        }
    </script>
</body>
</html>