/**
 * Created by lebedevas on 19.12.2016.
 */
function showStudentSubjects(userId, page){
    $.ajax({
        url: "MySubjects",
        type: "GET",
        data: {
            userId: userId,
            page: page
        },
        success: function(data){
            $("#specifyGroup").html(data);
        }
    });

    return false;
};

function hideInfo(){
    $("#specifyGroup").html("");
    return false;
};