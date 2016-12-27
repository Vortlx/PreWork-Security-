<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
	    <title>Students</title>
	    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
            integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="../../staticresources/css/formStyle.css"/>
        <link rel="stylesheet" type="text/css" href="../../staticresources/css/marginForButtons.css"/>
        
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	    <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
	    <script src="../../staticresources/javascript/showChangeGroup.js"></script>
	</head>
	<body>
	    <div class="container upMargin">
	        <div class="row">
	            <div class="col-sm-8">
		            <div class="link">
				        <a href="../welcome" class="btn btn-default" name="back">Back</a>
				    </div>
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
				    <script>
				        $(document).ready(function(){
				            // get userId from url
				            var parameters = window.location.search.substr(1);
				            var userId = 1// parameters.substr(parameters.indexOf("userId") + "userId".length + 1);
				            var page = parameters.substr(parameters.indexOf("page") + "page".length + 1);
				
				            $("#studentsList").dataTable({
				                ajax:{
				                    url: "../Students",
				                    type:"GET",
				                    data: {
				                    	userId: userId,
				                    	page: page
				                    },
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
				                                    "<a href=\"#\" name=\"changeGroup\"" +
				                                    " onclick=\"return showChangeGroup(" + userId +
				                                    ", " + row.id + ")\">Change group</a>";
				                        }
				                    },
				                    {
				                        targets: 3,
				                        searchable: false,
				                        orderable: false,
				                        render: function(data, type, row){
				                            return "<a href=\"../delete/DeleteStudent?studentId=" + row.id +
				                                "&userId=" + userId + "\" name=\"deleteStudent\">Delete</a>";
				                        }
				                    }
				                ]
				            });
				        })
				    </script>
	            </div>
	            <div class="col-sm-3">
		            <div id="changeGroupForm">
		            </div>
	            </div>
	        </div>
	        <div class="row">
               <div class="col-sm-offset-2 col-sm-1">
                   <a href="Students.jsp?userId=1&page=${param.page - 1}">prev</a>
               </div>
               <div class="col-sm-offset-2 col-sm-1">
               <a href="Students.jsp?userId=1&page=${param.page + 1}">next</a>
               </div>
            </div>
	    </div>
	</body>
</html>