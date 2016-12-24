/**
 * Created by lebedevas on 19.12.2016.
 */
function showStudentSubjects(userId){
    $.ajax({
        url: "MySubjects",
        type: "GET",
        data: {
            userId: userId
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
}