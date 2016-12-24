/**
 * Created by lebedevas on 13.12.2016.
 */
function addSwitcher(userId){
    var form = document.forms.add;
    var select = document.getElementById("whatAdd");

    if(select.options[0].selected){
        form.action = "add/AddGroup.jsp?userId=" + userId;
    }else if(select.options[1].selected){
        form.action = "add/AddStudent.jsp?userId=" + userId;
    }else{
        form.action = "add/AddTeacher.jsp?userId=" + userId;
    }
}