/**
 * Created by lebedevas on 15.12.2016.
 */
$(document).ready(function(){
    $("#addPerson").validate({
        rules:{
            personName:{
                required: true,
                pattern: true
            },
            personFamilyName:{
                required: true,
                pattern: true
            }
        },
        messages:{
            personName:{
                required: "Please enter name",
                pattern: "Name should have only letters in name"
            },
            personFamilyName:{
                required: "Please enter family name",
                pattern: "Family name should have only letters in name"
            }
        }
    });
});

$.validator.addMethod("pattern", function(val){
	var regExp = /[0-9]/;
	return !regExp.test(val);
});