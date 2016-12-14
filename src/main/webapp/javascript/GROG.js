/**
 * Created by lebedevas on 13.12.2016.
 */
alert(1);
function addSwitcher(){
    var form = document.forms.add;
    var select = document.getElementById("whatAdd");

    if(select.options[0].selected){
        form.action = "add/AddGroup.jsp?userId=${user.id}";
    }else if(select.options[1].selected){
        form.action = "add/AddStudent.jsp?userId=${user.id}";
    }else{
        form.action = "add/AddTeacher.jsp?userId=${user.id}";
    }
}