package prework.controller.update;


import prework.databaseservice.dao.DAOStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/jsp/update")
public class UpdateController {

    @Autowired
    private DAOStudent daoStudent;

    @RequestMapping(value = "/ChangeGroupServ", method = RequestMethod.POST)
    public String changeGroup(@RequestParam("studentID") int studentID, @RequestParam("newGroupName") String newGroupName,
                              Model model){

        String message = null;

        try{
            daoStudent.updateGroup(studentID, newGroupName);
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
            model.addAttribute("message", message);

            return "./ChangeGroup";
        }

        return "../search/StudentsSearch";
    }
}
