<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
	    <title>Teachers</title>
	    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"/>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	        integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	    <link rel="stylesheet" type="text/css" href="../../staticresources/css/formStyle.css"/>
	    <link rel="stylesheet" type="text/css" href="../../staticresources/css/marginForButtons.css"/>
	        
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	    <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
		<script src="../../staticresources/javascript/getUrlParameters.js"></script>
	</head>
	<body>
	    <div class="container upMargin">
	        <div class="row">
	            <div class="col-sm-11">
	                <div class="link">
	                    <a href="../welcome" class="btn btn-default" name="back">Back</a>
	                </div>
	                <div id="tableParent">
				        <table id="teachersList" class="table-bordered">
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
	            </div>
	        </div>
	        <div class="row paging">
				<div class="btn-toolbar">
					<div class="btn-group">
						<div class=" col-sm-1">
							<a href="Teachers.jsp?userId=${param.userId}&page=${param.page - 1}" class="btn btn-default">&lt;</a>
						</div>
					</div>
					<div class="btn-group">
						<a href="#" class="btn btn-default">...</a>
					</div>
					<div class="btn-group">
						<div class="col-sm-1">
							<a href="Teachers.jsp?userId=${param.userId}&page=${param.page + 1}" class="btn btn-default">&gt;</a>
						</div>
					</div>
				</div>
	        </div>
	    </div>
		<script>
            $(document).ready(function(){
                // Function return object with url parameters
                var pars = getUrlParameters(window.location);

                $("#teachersList").dataTable({
                    ajax:{
                        url: "../Teachers",
                        type: "GET",
                        data: {
                            userId: pars["userId"],
                            page: pars["page"]
                        },
                        dataSrc: ""
                    },
                    bFilter : false,
                    bLengthChange: false,
                    paging: false,
                    info: false,
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
                            data:function(row){
                                return row.subject.name + ": " + row.subject.type;
                            }
                        },
                        {
                            targets: 3,
                            searchable: false,
                            orderable: false,
                            render: function(data, type, row){
                                return "<a href=\"../delete/DeleteTeacher?teacherId=" + row.id +
                                    "&userId=" + pars["userId"] + "&page=" + pars["page"] + "\" name=\"deleteTeacher\">Delete</a>";
                            }
                        }
                    ]
                });
            })
		</script>
	</body>
</html>