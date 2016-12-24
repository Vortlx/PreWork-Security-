/**
 * Created by lebedevas on 23.12.2016.
 */
function showChangeGroup(userId, studentId){
    $.ajax({
        url: "../ChangeGroupPage",
        type: "GET",
        data:{
            userId: userId,
            studentId: studentId
        },
        success: function(data){
            $("#changeGroupForm").html(data);
        }
    });

    return false;
};

function hideFrom(){
    $("#changeGroupForm").html("");
    return false;
}