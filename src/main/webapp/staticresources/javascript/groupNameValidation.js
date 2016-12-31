/**
 * Created by lebedevas on 15.12.2016.
 */
$(document).ready(function () {
    $("#addGroup").validate({
        rules: {
        	groupName: {
                required: true,
                pattern: true
            }
        },
        messages: {
            groupName: {
                required: "Please enter group name",
                pattern: "Group name should have only numbers in name"
            }
        }
    });
});

$.validator.addMethod("pattern", function(val){
	var regExp = /[a-zA-Z]/;
	return !regExp.test(val); 
});