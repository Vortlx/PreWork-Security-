/**
 * Created by lebedevas on 15.12.2016.
 */
function showGroup(){
	$("#specifyGroup").html($.ajax({
		url: "MyGroup",
		type: "GET",
		async: true,
		data: {
			groupId: "<c:out value= ${group.id}/>",
			userId: "<c:out value= ${param.userId}/>"
		},
		success: function(){
			alert("SO All OK");
		}
	}));
	
	return false;
};