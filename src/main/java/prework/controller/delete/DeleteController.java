package prework.controller.delete;

import prework.data.Group;
import prework.data.Student;
import prework.data.Teacher;
import prework.databaseservice.dao.DAOGroup;
import prework.databaseservice.dao.DAOStudent;
import prework.databaseservice.dao.DAOTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import prework.databaseservice.dao.DAOUserInfo;

@Controller
@RequestMapping(value="/jsp")
public class DeleteController {

    @Autowired
    private DAOGroup daoGroup;
    
    @Autowired
    private DAOStudent daoStudent;
    
    @Autowired
    private DAOTeacher daoTeacher;

    @Autowired
    private DAOUserInfo daoUserInfo;
    
    @RequestMapping(value = "/DeleteStudent", method = RequestMethod.GET)
    public String deleteStudent(@RequestParam("studentId") int studentId, Model model){

        try{
            Student student = daoStudent.getById(studentId); 

            daoUserInfo.deleteByID(student.getUserInfo().getId());
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
            Group group = daoGroup.getByID(groupId);
            for(Student student: group.getStudents()){
                daoUserInfo.deleteByID(student.getUserInfo().getId());
            }

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
            Teacher teacher = daoTeacher.getById(teacherId);

            daoUserInfo.deleteByID(teacher.getUserInfo().getId());
            daoTeacher.deleteByID(teacher.getId());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "./search/Teachers";
        }
    }
}