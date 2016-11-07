package prework.controller.add;


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
@RequestMapping(value="/jsp/add")
public class AddController {

    @Autowired
    private DAOGroup daoGroup;
    
    @Autowired
    private DAOStudent daoStudent;
    
    @Autowired
    private DAOTeacher daoTeacher;

    
    @RequestMapping(value = "/AddCuratorServ", method = RequestMethod.POST)
    public String addCurator(@RequestParam("teacherID") int teacherID,
                             @RequestParam("groupName") String groupName, Model model){

        try{
            daoTeacher.addGroup(teacherID, groupName);
        }catch(Exception e){
            e.printStackTrace();

            String message = null;
            message = "Can't do this operation.";
            model.addAttribute("message", message);

            return "../add/AddCurator";
        }

        return "../search/TeachersSearch";
    }

    @RequestMapping(value = "/AddGroupServ", method = RequestMethod.POST)
    public String addGroup(@RequestParam("name") String groupName, Model model){

        String message = null;

        try{
            daoGroup.add(groupName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
        }finally{
            model.addAttribute("message", message);
            return "AddGroup";
        }
    }

    @RequestMapping(value = "/AddStudentServ", method = RequestMethod.POST)
    public String addStudent(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                             @RequestParam("group") String groupName, Model model){

        String message = null;

        try{
            daoStudent.add(name, familyName, groupName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
        }finally{
            model.addAttribute("message", message);
            return "AddStudent";
        }
    }

    @RequestMapping(value = "/AddTeacherServ", method = RequestMethod.POST)
    public String addTeacher(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                             Model model){

        String message = null;

        try{
            daoTeacher.add(name, familyName);
            message = "Operation was success";
        }catch(Exception e){
            e.printStackTrace();

            message = "Can't do this operation.";
        }finally{
            model.addAttribute("message", message);
            return "AddTeacher";
        }
    }
}