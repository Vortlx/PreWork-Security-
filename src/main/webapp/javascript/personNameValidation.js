/**
 * Created by lebedevas on 15.12.2016.
 */
function personIncorrectNameValidation(){
    var personName = document.getElementById("personName");
    var personFamilyName = document.getElementById("personFamilyName");

    var regExpr = /.*[0-9]+.*/i;

    clearErrorMessage();

    if(regExpr.test(personName.value)){
        var message = document.getElementById("incorrName");
        message.innerHTML = "Was entered incorrect name";
        return false;
    }else if(regExpr.test(personFamilyName.value)){
        var message = document.getElementById("incorrFamilyName");
        message.innerHTML = "Was entered incorrect family name";
        return false;
    }
    return true;
};

function personEmptyNameValidation(){
    var personName = document.getElementById("personName");
    var personFamilyName = document.getElementById("personFamilyName");

    clearErrorMessage();

    if(personName.value == ""){
        var message = document.getElementById("incorrName");
        message.innerHTML = "Please enter name";
        return false;
    }else if(personFamilyName.value == ""){
        var message = document.getElementById("incorrFamilyName");
        message.innerHTML = "Please enter family name";
        return false;
    }
    return true;
};

function allPersonValidation(){
    return personEmptyNameValidation() && personIncorrectNameValidation();
}

function clearErrorMessage(){
    var messageName = document.getElementById("incorrName");
    var messageFamilyName = document.getElementById("incorrFamilyName");

    messageName.innerHTML = "";
    messageFamilyName.innerHTML = "";
};