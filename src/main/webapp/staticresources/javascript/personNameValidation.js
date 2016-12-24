/**
 * Created by lebedevas on 15.12.2016.
 */
function personIncorrectNameValidation(){
    var personName = $("#personName");
    var personFamilyName = $("#personFamilyName");

    var regExpr = /.*[0-9]+.*/i;

    clearErrorMessage();

    if(regExpr.test(personName.val())){
        $("#incorrName").html("Was entered incorrect name");
        return false;
    }else if(regExpr.test(personFamilyName.val())){
        $("#incorrFamilyName").html("Was entered incorrect family name");
        return false;
    }
    return true;
};

function personEmptyNameValidation(){
    var personName = $("#personName");
    var personFamilyName = $("#personFamilyName");

    clearErrorMessage();

    if(personName.val() == ""){
        $("#incorrName").html("Please enter name");
        return false;
    }else if(personFamilyName.val() == ""){
        $("#incorrFamilyName").html("Please enter family name");
        return false;
    }
    return true;
};

function allPersonValidation(){
    return personEmptyNameValidation() && personIncorrectNameValidation();
};

function clearErrorMessage(){
    $("#incorrName").html("");
    $("#incorrFamilyName").html("");
};