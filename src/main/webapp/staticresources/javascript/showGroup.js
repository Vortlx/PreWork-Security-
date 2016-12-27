/**
 * Created by lebedevas on 15.12.2016.
 */
function showGroup(groupId, userId, page){
	// Create table
	$("#specifyGroup").html("<div class=\"link\">"+
							"<a href=\"/\" name=\"Back\" class=\"btn btn-default\" onclick=\"return hideInfo()\">Hide</a>" +
							"</div>" + 
							"<table id=\"groupInfo\" class=\"table-bordered\">" +
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
                userId: userId,
                page: page
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
};