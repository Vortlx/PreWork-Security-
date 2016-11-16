package prework.controller.delete;

import prework.data.Student;
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
@RequestMapping(value="/jsp")
public class DeleteController {

    @Autowired
    private DAOGroup daoGroup;
    
    @Autowired
    private DAOStudent daoStudent;
    
    @Autowired
    private DAOTeacher daoTeacher;
    
    @RequestMapping(value = "/DeleteStudent", method = RequestMethod.GET)
    public String deleteStudent(@RequestParam("studentId") int studentId, Model model){

        try{
            Student student = daoStudent.getById(studentId); 
            daoStudent.deleteByID(studentId);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "./search/Students";            
        }
    }

    @RequestMapping(value = "/DeleteGroup", method = RequestMethod.GET)
    public String deleteGroup(@RequestParam("groupId") int groupId, Model model){

        try{
            daoGroup.deleteByID(groupId);
        }catch(Exception e){
            e.printStackTrace();

        }finally{
            return "./search/Groups";            
        }
    }

    @RequestMapping(value = "/DeleteTeacher", method = RequestMethod.GET)
    public String deleteTeacher(@RequestParam("teacherId") int teacherId, Model model){

        try{
            daoTeacher.deleteByID(teacherId);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "./search/Teachers";
        }
    }
}