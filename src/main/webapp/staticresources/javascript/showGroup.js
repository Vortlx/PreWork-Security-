/**
 * Created by lebedevas on 15.12.2016.
 */
function showGroup(groupId, userId, page){
	$.ajax({
        url: "MyGroup.jsp",
        type: "GET",
        data: {
            groupId: groupId,
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