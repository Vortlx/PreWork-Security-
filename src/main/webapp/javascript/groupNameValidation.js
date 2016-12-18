/**
 * Created by lebedevas on 15.12.2016.
 */
function groupNameValidation(){
    var groupName = $("#groupName");

    var regExpr = /.*[a-zA-Z]+.*/;

    clearErrorMessage();
    if(groupName.val() == ""){
        $("#incorrGroupName").html("Please enter group name");
        return false;
    }else if(regExpr.test(groupName.val())){
        $("#incorrGroupName").html("Was entered incorrect group name");
        return false;
    }
    
    function clearErrorMessage(){
        $("#incorrGroupName").html("");
    };
};