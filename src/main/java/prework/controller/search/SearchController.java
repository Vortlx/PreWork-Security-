package prework.controller.search;


import prework.data.Group;
import prework.data.Student;
import prework.data.Teacher;
import prework.data.UserInfo;
import prework.databaseservice.dao.DAOGroup;
import prework.databaseservice.dao.DAOStudent;
import prework.databaseservice.dao.DAOTeacher;
import prework.databaseservice.dao.DAOUserInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/jsp/search")
public class SearchController {

    @Autowired
    private DAOGroup daoGroup;
    
    @Autowired
    private DAOStudent daoStudent;
    
    @Autowired
    private DAOTeacher daoTeacher;
    
    @Autowired
    private DAOUserInfo daoUserInfo;
    
    @RequestMapping(value = "/MyGroup", method = RequestMethod.GET)
    public String findMyGroup(@RequestParam("userId") String userId, Model model){

        try{
            UserInfo userInfo = daoUserInfo.getByID(Integer.parseInt(userId));
            Iterator<Student> iterator = userInfo.getStudents().iterator();
            Student student = iterator.next();
            
            model.addAttribute("group", student.getGroup());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "./MyGroup";
        }
    }

    @RequestMapping(value = "/FindGroupServ", method = RequestMethod.POST)
    public String findGroup(@RequestParam("name") String groupName, Model model){

        List<Group> groups = new ArrayList<Group>();

        try{
            if("".equals(groupName)){
                groups = daoGroup.getAll();
            }else if(!"".equals(groupName)) {
                Group oneGroup = daoGroup.getByName(groupName);
                groups.add(oneGroup);
            }
            model.addAttribute("groups", groups);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "GroupsSearch";
        }
    }

    @RequestMapping(value = "/FindStudentServ", method = RequestMethod.POST)
    public String findStudent(@RequestParam("name") String name, @RequestParam("familyName")  String familyName,
                              Model model){

        List<Student> students = new ArrayList<Student>();

        try{
            if("".equals(name) && "".equals(familyName)){
                students = daoStudent.getAll();
            }else if(!"".equals(name) && "".equals(familyName)){
                students = daoStudent.getByName(name);
            } else if("".equals(name) && !"".equals(familyName)){
                students = daoStudent.getByFamilyName(familyName);
            }else{
                
            }

            model.addAttribute("students", students);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "StudentsSearch";
        }
    }

    @RequestMapping(value = "/FindTeacherServ", method = RequestMethod.POST)
    public String findTeacher(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                              Model model){

        List<Teacher> teachers = new ArrayList<Teacher>();

        try{
            if("".equals(name) && "".equals(familyName)){
                teachers = daoTeacher.getAll();
            }else if(!"".equals(name) && "".equals(familyName)){
                teachers = daoTeacher.getByName(name);
            } else if("".equals(name) && !"".equals(familyName)){
                teachers = daoTeacher.getByFamilyName(familyName);
            }else{
                teachers = daoTeacher.getTeacher(name, familyName);
            }

            model.addAttribute("teachers", teachers);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "TeachersSearch";
        }
    }

    @RequestMapping(value = "/TeachersByGroupServ", method = RequestMethod.GET)
    public String findTeacherByGroup(@RequestParam("groupName") String groupName, Model model){

        List<Teacher> teachers = new ArrayList<Teacher>();

        try{
            

            model.addAttribute("groupName", groupName);
            model.addAttribute("teachers", teachers);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "TeachersByGroup";
        }
    }
}