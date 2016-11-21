package prework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import prework.dao.*;
import prework.entities.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "jsp")
public class TeacherController {

    @Autowired
    private DAOTeacher daoTeacher;

    @Autowired
    private DAOUser daoUser;

    @Autowired
    private DAODepartment daoDepartment;

    @Autowired
    private DAOSubject daoSubject;

    @Autowired
    private DAORole daoRole;

    @RequestMapping(value = "AddTeacher", method = RequestMethod.POST)
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

            return "add/AddTeacher.jsp";
        }

        return "welcome";
    }

    @RequestMapping(value = "DeleteTeacher", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String deleteTeacher(@RequestParam("teacherId") int teacherId, Model model) {

        try {
            Teacher teacher = daoTeacher.getById(teacherId);

            daoUser.deleteByID(teacher.getUser().getId());
            daoSubject.delete(teacher.getSubject().getId());
            daoTeacher.deleteByID(teacher.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/Teachers.jsp";
        }
    }

    @RequestMapping(value = "Teachers", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String findTeachers(@RequestParam(name = "userId", required = false) String userId,
                               Model model) {

        int userIdInt = Integer.parseInt(userId);
        Set<Teacher> teachers;
        Department department;

        try {
            User user = daoUser.getByID(userIdInt);
            department = user.getDepartment();

            teachers = department.getTeachers();

            model.addAttribute("department", department);
            model.addAttribute("teachers", teachers);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/Teachers.jsp";
        }
    }

    @RequestMapping(value = "Teachers", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String findTeachers(@RequestParam(name = "name", required = false) String name,
                               @RequestParam(name = "familyName", required = false) String familyName,
                               Model model) {

        List<Teacher> teachers;
        Department department;

        try {

            if (!"".equals(name) && !"".equals(familyName)) {
                teachers = daoTeacher.getTeacher(name, familyName);
            } else if (!"".equals(name)) {
                teachers = daoTeacher.getByName(name);
            } else if (!"".equals(familyName)) {
                teachers = daoTeacher.getByFamilyName(familyName);
            } else {
                teachers = daoTeacher.getAll();
            }

            department = teachers.get(0).getDepartment();

            model.addAttribute("department", department);
            model.addAttribute("teachers", teachers);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/Teachers.jsp";
        }
    }
}