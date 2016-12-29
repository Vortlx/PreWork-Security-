/**
 * Created by lebedevas on 15.12.2016.
 */
function showGroup(groupId, userId, page){
	$.ajax({
        url: "search/MyGroup.jsp",
        type: "GET",
        data: {
            userId: userId,
            groupId: groupId,
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