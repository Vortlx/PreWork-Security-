/**
 * Created by lebedevas on 15.12.2016.
 */
function showGroup(groupId, userId){

	// $.ajax({
     //    url: "MyGroup",
     //    type: "GET",
     //    data: {
     //        groupId: groupId,
     //        userId: userId
     //    },
	// 	success: function(data){
     //    	for(var par in data["students"][0]){
     //    		alert(par + " " + data["students"][0][par]);
	// 		}
	// 	}
	// });

	$("#specifyGroup").html("<table id=\"groupInfo\" border=\"1\">" +
								"<thead>" +
									"<tr>" +
										"<th colspan=\"2\">${group.name}</th>" +
									"</tr>" +
									"<tr>" +
										"<th>Name</th>" +
										"<th>Family Name</th>" +
									"</tr>" +
								"</thead>" +
        					"</table>");

	$("#groupInfo").dataTable({
		ajax: {
			url: "MyGroup",
            type: "GET",
            data: {
                groupId: groupId,
                userId: userId
            },
			aoColumns: [
				{mData: null},
                {mData: null},
				{mData: "id"},
				{mData: "name"}
			]
		}
	});
	return false;
};

function hideInfo(){
    $("#specifyGroup").html("");
    return false;
}