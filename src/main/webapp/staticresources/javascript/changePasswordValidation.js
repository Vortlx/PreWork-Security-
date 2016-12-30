/**
 * Created by lebedevas on 15.12.2016.
 */
$(document).ready(function(){
    $("#changePassword").validate({
        rules:{
            oldPassword: {
                required: true,
                minlength: 3
            },
            newPassword: {
                required: true,
                minlength: 3
            }
        },
        messages:{
            oldPassword: {
                required: "Please enter the old password",
                minlength: "Password must be not less then 3 character"
            },
            newPassword: {
                required: "Password not be empty",
                minlength: "Password must be not less then 3 character"
            }
        }
    })
})
