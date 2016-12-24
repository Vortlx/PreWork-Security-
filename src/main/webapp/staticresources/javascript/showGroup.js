/**
 * Created by lebedevas on 15.12.2016.
 */
function showGroup(groupId, userId){
	// Create table
	$("#specifyGroup").html("<a href=\"/\" name=\"Back\" onclick=\"return hideInfo()\">Hide</a>" +
							"<table id=\"groupInfo\" border=\"1\">" +
								"<thead>" +
									 // "<tr>" +
									 // 	"<th colspan=\"2\">${group.name}</th>" +
									 // "</tr>" +
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
            dataSrc: ""
		},
        columns: [
			{data: "name"},
			{data: "familyName"}
	    ]
	});
	return false;
};

function hideInfo(){
    $("#specifyGroup").html("");
    return false;
}