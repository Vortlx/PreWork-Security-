/**
 * Created by lebedevas on 15.12.2016.
 */
function showGroup(groupId, userId){
	$.ajax({
		url: "MyGroup",
		type: "GET",
		async: true,
		data: {
			groupId: groupId,
			userId: userId
		},
		success: function(data){
			$("#specifyGroup").html(data);
		}
	});
	
	return false;
};

function hideGroup(){
    $("#specifyGroup").html("");
    return false;
}