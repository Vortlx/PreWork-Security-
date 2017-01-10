<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp">
    <jsp:param name="userId" value="${param.userId}"/>
</jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>ChangePassword</title>

    <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>
    <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>

    <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>

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
                    <a href="welcome" class="btn btn-default" name="Back">Cancel</a>
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
</body>
</html>