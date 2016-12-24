<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
	    <title>Add Group</title>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	            integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	        integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	    <link rel="stylesheet" type="text/css" href="../../staticresources/css/FormStyle.css"/>
	
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	            
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	    <script src="../../staticresources/javascript/groupNameValidation.js"></script>
	</head>
	<body>
        <div id="back">
            <a href="../welcome" class="btn btn-default" name="back">Back</a>
        </div>
	    <form action="AddGroup" method="POST" class="form-horizontal" onsubmit="return groupNameValidation()">
		    <div class="form-group">
		        <label for="groupName" class="col-sm-2 control-label">Name: </label>
		        <div class="col-sm-10">
		           <input name="name" id="groupName" class="form-control" type="text" />
		        </div>
		        <span class="error" id="incorrGroupName"></span>
		    </div>
		    <div class="form-group">
			    <input name="userId" type="hidden" value="${param.userId}"/>
		        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
		        <div class="col-sm-offset-2 col-sm-10">
		           <input name="add" type="submit" class="btn btn-warning" value="Add">
		        </div>
		    </div>
	    </form>
	    <br>
        <div>
            <span class="error">${message}</span>
        </div>
	</body>
</html>