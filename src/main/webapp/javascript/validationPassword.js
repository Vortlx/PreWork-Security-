/**
 * Created by lebedevas on 15.12.2016.
 */
function validPassword(){
    var oldPassword = document.getElementById("oldPassword");
    var newPassword = document.getElementById("newPassword");

    clearErrorMessage();
    if(oldPassword.value == ""){
        var message = document.getElementById("incorrOldPass");
        message.innerHTML = "Please enter the old password";
        return false;
    }else if(oldPassword.value.length < 3){
        var message = document.getElementById("incorrOldPass");
        message.innerHTML = "Password must be not less then 3 character";
        return false;
    }else if(newPassword.value == ""){
        var message = document.getElementById("incorrNewPass");
        message.innerHTML = "Password not be empty";
        return false;
    }else if(newPassword.value.length < 3){
        var message = document.getElementById("incorrNewPass");
        message.innerHTML = "Password must be not less then 3 character";
        return false;
    }

    function clearErrorMessage(){
        var oldPass = document.getElementById("incorrOldPass");
        var newPass = document.getElementById("incorrNewPass");

        oldPass.innerHTML = "";
        newPass.innerHTML = "";
    }
}