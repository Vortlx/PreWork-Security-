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

/**
 * Created by lebedevas on 15.12.2016.
 */
$(document).ready(function(){
    $("#addPerson").validate({
        riles:{
            personName:{
                required: true
            },
            personFamilyName:{
                required: true
            }
        },
        messages:{
            personName:{
                required: "Please enter name"
            },
            personFamilyName:{
                required: "Please enter family name"
            }
        }
    });
});