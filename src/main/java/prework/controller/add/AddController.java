package prework.controller.add;


import org.springframework.security.access.prepost.PreAuthorize;
import prework.data.*;
import prework.databaseservice.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.Set;


@Controller
@RequestMapping(value="/jsp")
public class AddController {

    @Autowired
    private DAOGroup daoGroup;
    
    @Autowired
    private DAOStudent daoStudent;
    
    @Autowired
    private DAOTeacher daoTeacher;

    @Autowired
    private DAOUserInfo daoUserInfo;

    @Autowired
    private DAODepartment daoDepartment;

    @Autowired
    private DAORole daoRole;
    
    @Autowired
    private DAOSubject daoSubject;


    // Rewrite via Enam
    @RequestMapping(value="/Add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String add(@RequestParam("whatAdd") String whatAdd,
                      @RequestParam("userId") String userId, Model model){

        UserInfo userInfo = daoUserInfo.getByID(Integer.parseInt(userId));

        Iterator<Department> iterator = userInfo.getDepartments().iterator();
        Department department = iterator.next();

        if("GROUP".equals(whatAdd)){
            model.addAttribute("departmentId", department.getId());
            return "./add/AddGroup";
        }else if("STUDENT".equals(whatAdd)){
            model.addAttribute("groups", department.getGroups());
            return "./add/AddStudent";
        }else if("TEACHER".equals(whatAdd)){
            model.addAttribute("departmentId", department.getId());
            return "./add/AddTeacher";
        }else{
            return "./welcome";
        }
    }

    @RequestMapping(value = "/AddStudent", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String addStudent(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                             @RequestParam("groupID") int groupId,
                             Model model){

        int groupIdInt = groupId;

        try{
            Role role = daoRole.getByName("ROLE_STUDENT");

            UserInfo newUser = new UserInfo();
            newUser.setUsername(familyName + name);
            newUser.setPassword("test");
            newUser.setEnabled(1);
            newUser.setRole(role);

            daoUserInfo.add(newUser);
            
            Group group = daoGroup.getByID(groupId);
            daoStudent.add(name, familyName, group.getId(), daoUserInfo.getByUsername(familyName + name));

            model.addAttribute("userInfo", group.getDepartment().getUserInfo());
        }catch(Exception e){
            e.printStackTrace();
            String message = "Can't do this operation.";

            model.addAttribute("message", message);
            model.addAttribute("groupID", daoGroup.getAll());
            
            return "./add/AddStudent";
        }

        return "./welcome";
    }
    
    @RequestMapping(value = "/AddGroup", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String addGroup(@RequestParam("name") String groupName,
                           @RequestParam("departmentId") int depId,
                           Model model){
       try{
           Department department = daoDepartment.getByID(depId);
           daoGroup.add(groupName, department);
           
           model.addAttribute("userInfo", department.getUserInfo());
        }catch(Exception e){
           e.printStackTrace();

           String message = "Can't do this operation.";
           model.addAttribute("message", message);
           model.addAttribute("departmentId", depId);

           return "./add/AddGroup";
        }

        return "./welcome";
    }

    @RequestMapping(value = "/AddTeacher", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String addTeacher(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                             @RequestParam("subjectName") String subjectName,
                             @RequestParam("subjectType") String subjectType,
                             @RequestParam("departmentId") String depId,
                             Model model){
        try{
            Department department = daoDepartment.getByID(Integer.parseInt(depId));

            Subject newSubject = new Subject();
            newSubject.setName(subjectName);
            newSubject.setType(SubjectType.valueOf(subjectType));
            daoSubject.add(newSubject);

            Role role = daoRole.getByName("ROLE_TEACHER");

            UserInfo newUser = new UserInfo();
            newUser.setUsername(familyName + name);
            newUser.setPassword("test");
            newUser.setEnabled(1);
            newUser.setRole(role);
            daoUserInfo.add(newUser);
            
            daoTeacher.add(name, familyName, daoSubject.getByNameAndType(newSubject.getName(), newSubject.getType()),
                    department, daoUserInfo.getByUsername(familyName + name));
            
            model.addAttribute("userInfo", department.getUserInfo());
        }catch(Exception e){

            e.printStackTrace();
            String message = "Can't do this operation.";

            model.addAttribute("message", message);
            model.addAttribute("departmentId", depId);

            return "./add/AddTeacher";
        }

        return "./welcome";
    }

    @RequestMapping(value="/AddSubject", method=RequestMethod.POST)
    public String addSubject(@RequestParam("subjectName") String subjectName,
                             @RequestParam("subjectType") String subjectType,
                             @RequestParam("groupId") int groupId,
                             @RequestParam("userId") int userId,
                             Model model){
        try{
            Subject subject = daoSubject.getByNameAndType(subjectName, SubjectType.valueOf(subjectType));
            daoGroup.addSubject(groupId, subject);
        }catch(Exception e){
            e.printStackTrace();

            String message = "Can't do this operation.";
            model.addAttribute("message", message);

            return "./AddSubject";
        }finally{
            model.addAttribute("groupId", groupId);
            model.addAttribute("userId", userId);
        }

        return "./search/MySubjects";
    }

    @RequestMapping(value="/AddSubjectPage", method=RequestMethod.GET)
    public String addSubjectPage(@RequestParam("groupId") int groupId,
                                 @RequestParam("userId") int userId,
                                 Model model){
        try{
            Set<Subject> subjects = daoSubject.getAll();

            model.addAttribute("subjects", subjects);
            model.addAttribute("groupId", groupId);
            model.addAttribute("userId", userId);
        }catch(Exception e){
            e.printStackTrace();
        }

        return "./add/AddSubject";
    }
}