/**
 * Created by lebedevas on 15.12.2016.
 */
function validPassword(){
    var oldPassword = document.getElementById("oldPassword");
    var newPassword = document.getElementById("newPassword");

    if(oldPassword.value == ""){
        var message = document.getElementById("incorrOldPass");
        message.innerHTML = "   Please enter old password";
        return false;
    }else if(oldPassword.value.length <= 3){
        var message = document.getElementById("incorrOldPass");
        message.innerHTML = "   Password must be not less then 4 character";
        return false;
    }else if(newPassword.value == ""){
        var message = document.getElementById("incorrNewPass");
        message.innerHTML = "   Password not be empty";
        return false;
    }else if(newPassword.value.length <= 3){
        var message = document.getElementById("incorrNewPass");
        message.innerHTML = "   Password must be not less then 4 character";
        return false;
    }
}