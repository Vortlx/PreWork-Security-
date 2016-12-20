/**
 * Created by lebedevas on 15.12.2016.
 */
function showGroup(groupId, userId){
	$.ajax({
		url: "MyGroup",
		type: "GET",
		data: {
			groupId: groupId,
			userId: userId
		},
		success: function(data){
			//alert(data);
			$("#specifyGroup").html(JSON.parse(data));
		}
	});
	
	return false;
};

function hideInfo(){
    $("#specifyGroup").html("");
    return false;
}