/**
 * Created by lebedevas on 15.12.2016.
 */
function groupNameValidation(){
    var groupName = document.getElementById("groupName");

    var regExpr = /.*[a-zA-Z]+.*/;

    clearErrorMessage();
    if(groupName.value == ""){
        var message = document.getElementById("incorrGroupName");
        message.innerHTML = "Please enter group name";
        return false;
    }else if(regExpr.test(groupName.value)){
        var message = document.getElementById("incorrGroupName");
        message.innerHTML = "Was entered incorrect group name";
        return false;
    }
};

function clearErrorMessage(){
    var message = document.getElementById("incorrGroupName");

    message.innerHTML = "";
}
