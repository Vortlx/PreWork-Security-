<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Groups</title>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
    <script src="../javascript/showGroup.js"></script>
</head>
<body>
    <div style="float: left; margin-right: 50px">
	    <a href="welcome" name="back">Back</a>
	    <table id="tableGroups" border="1">
			<thead>
				<tr>
					<th>Name</th>
					<sec:authorize access="hasRole('ROLE_DEPARTMENT')">
						<th> </th>
						<th> </th>
					</sec:authorize>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${groups}" var="group">
					<tr>
						<td>
							<a href="/" name="toGroups"
							   onclick="return showGroup(${group.id}, ${param.userId})">${group.name}</a>
						</td>
						<sec:authorize access="hasRole('ROLE_DEPARTMENT')">
							<td>
								<a href="MySubjects?userId=${userId}&groupId=${group.id}" name="subjects">Subjects</a>
							</td>
							<td>
								<a href="DeleteGroup?userId=${userId}&groupId=${group.id}" name="deleteGroup">Delete</a>
							</td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</tbody>
	    </table>
		<script>
            $(function(){
                $("#tableGroups").dataTable();
            })
		</script>
    </div>
    <div id="specifyGroup">
    </div>
</body>
</html>