<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>ChangePassword</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
            
    <script src="../staticresources/javascript/changePasswordValidation.js"></script>
</head>
<body>
<div id="header"></div>
<div class="container upMargin">
    <form id="changePassword" name="newPassword" action="ChangePassword" method="POST" class="form-horizontal">
        <div class="form-group">
            <div class="row">
                <label for="oldPassword" class="col-sm-2 control-label">Enter your old password:</label>
                <div class="col-sm-7">
                    <input name="oldPassword" id="oldPassword" class="form-control" type="password"/>
                </div>
                <div class="col-sm-3">
                    <span class="error" id="incorrOldPass"></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <label for="newPassword" class="col-sm-2 control-label">Enter your new password:</label>
                <div class="col-sm-7">
                    <input name="newPassword" id="newPassword" class="form-control" type="password"/>
                </div>
                <div class="col-sm-3">
                    <span class="error" id="incorrNewPass"></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <input name="userId" type="hidden" value="${param.userId}"/>
            <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
            <div class="row">
                <div class="col-sm-offset-2 col-sm-1">
                    <input name="change" type="submit" class="btn btn-warning" value="Change"/>
                </div>
                <div class="col-sm-9">
                    <a href="welcome" class="btn btn-default" name="Back">Back</a>
                </div>
            </div>
        </div>
    </form>
    <div class="row">
        <div class="col-sm-offset-1 col-sm-11">
            <span class="error">${message}</span>
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
    	$("#header").load("welcome nav");
    });
</script>
</body>
</html>