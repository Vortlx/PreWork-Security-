package prework.controller.delete;

import prework.databaseservice.dao.DAOGroup;
import prework.databaseservice.dao.DAOStudent;
import prework.databaseservice.dao.DAOTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/jsp/delete")
public class DeleteController {

    @Autowired
    private DAOGroup daoGroup;
    
    @Autowired
    private DAOStudent daoStudent;
    
    @Autowired
    private DAOTeacher daoTeacher;

    
    @RequestMapping(value = "/DeleteCuratorServ", method = RequestMethod.POST)
    public String deleteCurator(@RequestParam("teacherID") int teacherID, @RequestParam("groupName") String groupName,
                                Model model){

        String message = null;

        try{
            daoTeacher.deleteCurator(teacherID, groupName);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "../search/TeachersSearch";
        }
    }

    @RequestMapping(value = "/DeleteGroupServ", method = RequestMethod.POST)
    public String deleteGroup(@RequestParam("name") String groupName, Model model){

        String message = null;

        try{
            daoGroup.delete(groupName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
        }finally{
            model.addAttribute("message", message);
            return "DeleteGroup";
        }
    }

    @RequestMapping(value = "/DeleteStudentServ", method = RequestMethod.POST)
    public String deleteStudent(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                                Model model){

        String message = null;

        try{
            daoStudent.delete(name, familyName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
        }finally{
            model.addAttribute("message", message);
            return "DeleteStudent";
        }
    }

    @RequestMapping(value = "/DeleteTeacherServ", method = RequestMethod.POST)
    public String deleteTeacher(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                                Model model){

        String message = null;

        try{
            daoTeacher.delete(name, familyName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
        }finally{
            model.addAttribute("message", message);
            return "DeleteTeacher";
        }
    }
}