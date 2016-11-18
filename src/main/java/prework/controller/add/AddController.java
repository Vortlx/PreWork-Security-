package prework.controller.add;


import org.springframework.security.access.prepost.PreAuthorize;
import prework.entities.*;
import prework.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping(value = "/jsp")
public class AddController {

    @Autowired
    private DAOGroup daoGroup;

    @Autowired
    private DAOStudent daoStudent;

    @Autowired
    private DAOTeacher daoTeacher;

    @Autowired
    private DAOUser daoUser;

    @Autowired
    private DAODepartment daoDepartment;

    @Autowired
    private DAORole daoRole;

    @Autowired
    private DAOSubject daoSubject;


    // Rewrite via Enam
    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String add(@RequestParam("whatAdd") String whatAdd,
                      @RequestParam("userId") String userId, Model model) {

        User user = daoUser.getByID(Integer.parseInt(userId));

        Department department = user.getDepartment();

        if ("GROUP".equals(whatAdd)) {
            model.addAttribute("departmentId", department.getId());
            return "./add/AddGroup.jsp";
        } else if ("STUDENT".equals(whatAdd)) {
            model.addAttribute("groups", department.getGroups());
            return "./add/AddStudent.jsp";
        } else if ("TEACHER".equals(whatAdd)) {
            model.addAttribute("departmentId", department.getId());
            return "./add/AddTeacher.jsp";
        } else {
            return "./welcome";
        }
    }

    @RequestMapping(value = "/AddStudent", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String addStudent(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                             @RequestParam("groupID") int groupId,
                             Model model) {

        int groupIdInt = groupId;

        try {
            Role role = daoRole.getByName("ROLE_STUDENT");

            User newUser = new User();
            newUser.setUsername(familyName + name);
            newUser.setPassword("test");
            newUser.setEnabled(1);
            newUser.setRole(role);

            daoUser.add(newUser);

            Group group = daoGroup.getByID(groupId);
            daoStudent.add(name, familyName, group.getId(), daoUser.getByUsername(familyName + name));

            model.addAttribute("user", group.getDepartment().getUser());
        } catch (Exception e) {
            e.printStackTrace();
            String message = "Can't do this operation.";

            model.addAttribute("message", message);
            model.addAttribute("groupID", daoGroup.getAll());

            return "./add/AddStudent.jsp";
        }

        return "./welcome";
    }

    @RequestMapping(value = "/AddGroup", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String addGroup(@RequestParam("name") String groupName,
                           @RequestParam("departmentId") int depId,
                           Model model) {
        try {
            Department department = daoDepartment.getByID(depId);
            daoGroup.add(groupName, department);

            model.addAttribute("user", department.getUser());
        } catch (Exception e) {
            e.printStackTrace();

            String message = "Can't do this operation.";
            model.addAttribute("message", message);
            model.addAttribute("departmentId", depId);

            return "./add/AddGroup.jsp";
        }

        return "./welcome";
    }

    @RequestMapping(value = "/AddTeacher", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String addTeacher(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                             @RequestParam("subjectName") String subjectName,
                             @RequestParam("subjectType") String subjectType,
                             @RequestParam("departmentId") String depId,
                             Model model) {
        try {
            Department department = daoDepartment.getByID(Integer.parseInt(depId));

            Subject newSubject = new Subject();
            newSubject.setName(subjectName);
            newSubject.setType(SubjectType.valueOf(subjectType));
            daoSubject.add(newSubject);

            Role role = daoRole.getByName("ROLE_TEACHER");

            User newUser = new User();
            newUser.setUsername(familyName + name);
            newUser.setPassword("test");
            newUser.setEnabled(1);
            newUser.setRole(role);
            daoUser.add(newUser);

            daoTeacher.add(name, familyName, daoSubject.getByNameAndType(newSubject.getName(), newSubject.getType()),
                    department, daoUser.getByUsername(familyName + name));

            model.addAttribute("user", department.getUser());
        } catch (Exception e) {

            e.printStackTrace();
            String message = "Can't do this operation.";

            model.addAttribute("message", message);
            model.addAttribute("departmentId", depId);

            return "./add/AddTeacher.jsp";
        }

        return "./welcome";
    }

    @RequestMapping(value = "/AddSubject", method = RequestMethod.POST)
    public String addSubject(@RequestParam("subjectName") String subjectName,
                             @RequestParam("subjectType") String subjectType,
                             @RequestParam("groupId") int groupId,
                             @RequestParam("userId") int userId,
                             Model model) {

        try {
            Group group = daoGroup.getByID(groupId);
            Subject subject = daoSubject.getByNameAndType(subjectName, SubjectType.valueOf(subjectType));
            daoSubject.addGroup(subject.getId(), group);

        } catch (Exception e) {
            e.printStackTrace();

            String message = "Can't do this operation.";

            model.addAttribute("message", message);
            model.addAttribute("subjects", daoSubject.getAll());

            return "./add/AddSubject.jsp";
        } finally {
            model.addAttribute("groupId", groupId);
            model.addAttribute("userId", userId);
        }

        return "./MySubjects";
    }

    @RequestMapping(value = "/AddSubjectPage", method = RequestMethod.GET)
    public String addSubjectPage(@RequestParam("groupId") int groupId,
                                 @RequestParam("userId") int userId,
                                 Model model) {
        try {
            List<Subject> subjects = daoSubject.getAll();

            model.addAttribute("subjects", subjects);
            model.addAttribute("groupId", groupId);
            model.addAttribute("userId", userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "./add/AddSubject.jsp";
    }
}