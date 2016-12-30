/**
 * Created by lebedevas on 15.12.2016.
 */
$(document).ready(function () {
    $("#addGroup").validate({
        rules: {
            groupName: {
                required: true,
                minlength: 3
            }
        },
        messages: {
            groupName: {
                required: "Please enter group name",
                minlength: "I don't know"
            }
        }
    });
});