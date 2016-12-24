/**
 * Created by lebedevas on 19.12.2016.
 */
function showAddSubjects(groupId, userId){
    $.ajax({
        url: "AddSubjectPage",
        type: "GET",
        data: {
            groupId: groupId,
            userId: userId
        },
        success: function(data){
            $("#newSubject").html(data);
        }
    });

    return false;
};

function hideAddSubject(){
    $("#newSubject").html("");
    return false;
}