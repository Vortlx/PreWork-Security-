<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <title>Welcome</title>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	        integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	    <link rel="stylesheet" type="text/css" href="staticresources/css/FormStyle.css"/>
	        
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	</head>
	<body>
	    <form action="j_spring_security_check" class="form-horizontal" method="POST">
		    <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">Login: </label>
			    <div class="col-sm-10">
			        <input name="username" id="username" class="form-control" type="text"/>
			    </div>
		    </div>
		    <div class="form-group">
		        <label for="password" class="col-sm-2 control-label">Password: </label>
		        <div class="col-sm-10">
		           <input name="password" id="password" class="form-control" type="password">
		        </div>
		    </div>
		    <div class="form-group">
		        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
		        <div class="col-sm-offset-2 col-sm-10">
		           <input name="login" type="submit" class="btn btn-warning" value="Login"/>
		        </div>
		    </div>
	    </form>
	</body>
</html>