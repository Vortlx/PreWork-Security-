package prework.controller.search;


import prework.data.*;
import prework.databaseservice.dao.DAOGroup;
import prework.databaseservice.dao.DAOStudent;
import prework.databaseservice.dao.DAOTeacher;
import prework.databaseservice.dao.DAOUserInfo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/jsp")
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
    public String findMyGroup(@RequestParam(name = "userId", required = false) String userId,
                              @RequestParam(name = "groupId", required = false) String groupId, Model model){

        try{
            if(groupId != null){
                Group group = daoGroup.getByID(Integer.parseInt(groupId));

                model.addAttribute("group", group);

            }else{
                UserInfo userInfo = daoUserInfo.getByID(Integer.parseInt(userId));
                Iterator<Student> iterator = userInfo.getStudents().iterator();
                Student student = iterator.next();

                model.addAttribute("group", student.getGroup());
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "./search/MyGroup";
        }
    }
    
    @RequestMapping(value = "/MySubjects", method = RequestMethod.GET)
    public String findMySubjects(@RequestParam("userId") String userId, Model model){

        try{
            UserInfo userInfo = daoUserInfo.getByID(Integer.parseInt(userId));
            Iterator<Student> iterator = userInfo.getStudents().iterator();
            Student student = iterator.next();
            
            model.addAttribute("subjects", student.getGroup().getSubjects());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "./search/MySubjects";
        }
    }

    @RequestMapping(value = "/Groups", method = RequestMethod.GET)
    public String findGroups(@RequestParam("userId") String userId, Model model){

        int userInfoIdInt = Integer.parseInt(userId);
        Set<Group> groups = null;
        Department department = null;
        Teacher teacher = null;

        try{
            UserInfo userInfo = daoUserInfo.getByID(userInfoIdInt);

            if(userInfo.getDepartments().size() > 0){
                Iterator<Department> iterator = userInfo.getDepartments().iterator();
                department = iterator.next();
                groups = department.getGroups();
            }else{
                Iterator<Teacher> iterator = userInfo.getTeachers().iterator();
                teacher = iterator.next();
                groups = teacher.getSubject().getGroups();
            }

            model.addAttribute("userId", userInfoIdInt);
            model.addAttribute("groups", groups);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "./search/Groups";
        }
    }

    // Need rewrite
    @RequestMapping(value = "/Students", method = RequestMethod.GET)
    public String findStudents(@RequestParam(name = "userId", required=false) String userId,
                               Model model){

        int userIdInt = Integer.parseInt(userId);
        Set<Student> students;
        Department department;

        try{

            UserInfo userInfo = daoUserInfo.getByID(userIdInt);
            Iterator<Department> iterator = userInfo.getDepartments().iterator();
            department = iterator.next();

            students = new HashSet<Student>();
            for(Group group: department.getGroups()){
                students.addAll(group.getStudents());
            }

            model.addAttribute("department", department);
            model.addAttribute("students", students);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "./search/Students";
        }
    }

    @RequestMapping(value = "/Students", method = RequestMethod.POST)
    public String findStudents(@RequestParam(name="name", required=false) String name,
                               @RequestParam(name="familyName", required = false) String familyName,
                               Model model){

        Set<Student> students;
        Department department;

        try{
            if(!"".equals(name) && !"".equals(familyName)){
                students = daoStudent.getStudent(name, familyName);
            } else if(!"".equals(name)){
                students = daoStudent.getByName(name);
            } else if(!"".equals(familyName)){
                students = daoStudent.getByFamilyName(familyName);
            } else{
                students = daoStudent.getAll();
            }

            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            Iterator<Student> iterator = students.iterator();
            department = iterator.next().getGroup().getDepartment();

            model.addAttribute("department", department);
            model.addAttribute("students", students);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "./search/Students";
        }
    }

    @RequestMapping(value = "/Teachers", method = RequestMethod.GET)
    public String findTeachers(@RequestParam(name = "userId", required=false) String userId,
                              Model model){

        int userIdInt = Integer.parseInt(userId);
        Set<Teacher> teachers;
        Department department;

        try{
            UserInfo userInfo = daoUserInfo.getByID(userIdInt);
            Iterator<Department> iterator = userInfo.getDepartments().iterator();
            department = iterator.next();

            teachers = department.getTeachers();

            model.addAttribute("department", department);
            model.addAttribute("teachers", teachers);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "./search/Teachers";
        }
    }

    @RequestMapping(value = "/Teachers", method = RequestMethod.POST)
    public String findTeachers(@RequestParam(name="name", required=false) String name,
                               @RequestParam(name="familyName", required = false) String familyName,
                               Model model){

        Set<Teacher> teachers;
        Department department;

        try{

            if(!"".equals(name) && !"".equals(familyName)){
                teachers = daoTeacher.getTeacher(name, familyName);
            } else if(!"".equals(name)){
                teachers = daoTeacher.getByName(name);
            } else if(!"".equals(familyName)){
                teachers = daoTeacher.getByFamilyName(familyName);
            } else{
                teachers = daoTeacher.getAll();
            }

            Iterator<Teacher> iterator = teachers.iterator();
            department = iterator.next().getDepartment();


            model.addAttribute("department", department);
            model.addAttribute("teachers", teachers);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return "./search/Teachers";
        }
    }
}