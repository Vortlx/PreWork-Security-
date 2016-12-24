<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
	    <title>Add Teacher</title>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	            integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	        integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="../../staticresources/css/FormStyle.css"/>
        	
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	    <script src="../../staticresources/javascript/personNameValidation.js"></script>
	</head>
	<body>
        <div id="back">
            <a href="../welcome" class="btn btn-default" name="back">Back</a>
        </div>
	    <form action="AddTeacher" method="POST" class="form-horizontal" onsubmit="return allPersonValidation()">
	        <div class="form-group">
	            <label for="personName" class="col-sm-2 control-label">Name:</label>
	            <div class="col-sm-10">
	               <input name="name" id="personName" class="form-control" type="text"/>
	            </div>
                <span class="error" id="incorrName"></span>
	        </div>
            <div class="form-group">
                <label for="personFamilyName" class="col-sm-2 control-label">Family Name:</label>
                <div class="col-sm-10">
                    <input name="familyName" id="personFamilyName" class="form-control" type="text"/>
                </div>
                <span class="error" id="incorrFamilyName"></span>
            </div>
	        <div class="form-group">
		        <label for="subjectName" class="col-sm-2 control-label">Subject:</label>
		        <div class="col-sm-10">
		          <input name="subjectName" id="subjectName" class="form-control" type="text"/>
		        </div>
		        <div>
			        <select name="subjectType">
	                    <option value="LECTURE">Lecture</option>
	                    <option value="PRACTICE">Practice</option>
	                </select>
		        </div>
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