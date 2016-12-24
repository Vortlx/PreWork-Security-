/**
 * Created by lebedevas on 15.12.2016.
 */
function validPassword(){
    var oldPassword = $("#oldPassword");
    var newPassword = $("#newPassword");

    clearErrorMessage();
    if(oldPassword.val() == ""){
    	$("#incorrOldPass").html("Please enter the old password");
        return false;
    }else if(oldPassword.val().length < 3){
    	$("#incorrOldPass").html("Password must be not less then 3 character");
        return false;
    }else if(newPassword.val() == ""){
    	$("#incorrNewPass").html("Password not be empty");
        return false;
    }else if(newPassword.val().length < 3){
    	$("#incorrNewPass").html("Password must be not less then 3 character");
        return false;
    }

    function clearErrorMessage(){
        $("#incorrOldPass").html("");
        $("#incorrNewPass").html("");
    };
};