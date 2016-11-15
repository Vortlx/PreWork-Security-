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
    
    @RequestMapping(value = "/DeleteStudent", method = RequestMethod.GET)
    public String deleteStudent(@RequestParam("studentId") int studentId, Model model){

        try{
            daoStudent.deleteByID(studentId);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "../search/MyGroup";            
        }
    }

    @RequestMapping(value = "/DeleteGroup", method = RequestMethod.GET)
    public String deleteGroup(@RequestParam("groupId") String groupId, Model model){

        try{
            daoGroup.deleteByID(Integer.parseInt(groupId));
        }catch(Exception e){
            e.printStackTrace();

        }finally{
            return "../search/Groups";            
        }
    }

    @RequestMapping(value = "/DeleteTeacher", method = RequestMethod.GET)
    public String deleteTeacher(@RequestParam("teacherId") String teacherId, Model model){

        try{
            daoTeacher.deleteByID(Integer.parseInt(teacherId));
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "../search/Teachers";
        }
    }
}