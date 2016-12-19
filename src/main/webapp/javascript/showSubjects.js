/**
 * Created by lebedevas on 19.12.2016.
 */
function showSubjects(groupId, userId){
    $.ajax({
        url: "AddSubjectPage",
        type: "GET",
        async: true,
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