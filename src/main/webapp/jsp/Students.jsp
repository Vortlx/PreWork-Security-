<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp">
	<jsp:param name="userId" value="${param.userId}"/>
</jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
	    <title>Students</title>

        <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>
        <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>

	    <script src="../staticresources/javascript/showChangeGroup.js"></script>
		<script src="../staticresources/javascript/getUrlParameters.js"></script>

	</head>
	<body>
        <div id="header"></div>
	    <div class="container upMargin">
	        <div class="row">
	            <div class="col-sm-8">
				    <div id="tableParent">
				        <table id="studentsList" class="table-bordered">
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
	            </div>
	            <div class="col-sm-3">
		            <div id="changeGroupForm">
		            </div>
	            </div>
	        </div>
	        <div class="row paging">
				<div class="btn-toolbar">
					<div id="prevPage" class="btn-group">
						<div class="col-sm-1">
							<a href="Students.jsp?userId=${param.userId}&page=${param.page - 1}" class="btn btn-default" id="pageLeft">&lt;</a>
						</div>
					</div>
					<div id="pageButtons" class="btn-group">
					</div>
					<div id="nextPage" class="btn-group">
						<div class="col-sm-1">
							<a href="Students.jsp?userId=${param.userId}&page=${param.page + 1}" class="btn btn-default" id="pageRight">&gt;</a>
						</div>
					</div>
				</div>
            </div>
	    </div>
		<script>
            var pars = getUrlParameters(window.location);

            $(document).ready(function(){
				// Function return object with url parameters
				drawTable(pars);

            });

            function drawTable(pars){
                $("#studentsList").dataTable({
                    ajax:{
                        url: "Students",
                        type:"GET",
                        data: {
                            userId: pars["userId"],
                            page: pars["page"]
                        },
                        dataSrc: "students"
                    },
                    destroy: true,
                    bFilter : false,
                    bLengthChange: false,
                    paging: false,
                    info: false,
                    serverSide: true,
                    columnDefs: [
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
                                    "<a href=\"#\" name=\"changeGroup\"" +
                                    " onclick=\"return showChangeGroup(" + pars["userId"] +
                                    ", " + row.id + ")\">Change group</a>";
                            }
                        },
                        {
                            targets: 3,
                            searchable: false,
                            orderable: false,
                            render: function(data, type, row){
                                if(pars["page"] == undefined){
                                    return "<a href=\"DeleteStudent?studentId=" + row.id +
                                        "&userId=" + pars["userId"] + "\" name=\"deleteStudent\">Delete</a>";
                                }else{
                                    return "<a href=\"DeleteStudent?studentId=" + row.id +
                                        "&userId=" + pars["userId"] + "&page=" + pars["page"] + "\" name=\"deleteStudent\">Delete</a>";
                                }

                            }
                        }
                    ],
                    fnInitComplete: function(setting, json){
                        if(json.page <= 1){
                            $("#pageLeft").hide();
                        } else if(json.page >= json.maxPage){
                            $("#pageRight").hide();
                        }

                        $("#pageButtons").html(function(){
                            var buttons = "";

                            for(var i = 1; i <= json.maxPage; i++){
                                if(i == json.page){
                                    buttons += "<a href=\"Students.jsp?userId=" +
                                        pars["userId"] + "&page=" + i +
                                        "\" class=\"btn btn-default active\">" + i + "</a>"
                                } else{
                                    buttons += "<a href=\"Students.jsp?userId=" +
                                        pars["userId"] + "&page=" + i +
                                        "\" class=\"btn btn-default\">" + i + "</a>"
                                }
                            }
                            return buttons;
                        })
                    }
                });
			};
		</script>
	</body>
</html>