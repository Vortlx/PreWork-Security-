package prework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import prework.entities.*;
import prework.service.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "jsp")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;


    @RequestMapping(value = "AddTeacher", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String addTeacher(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                             @RequestParam("subjectName") String subjectName,
                             @RequestParam("subjectType") String subjectType,
                             @RequestParam("departmentId") int depId,
                             Model model) {
        try {
            Subject newSubject = subjectService.add(subjectName, SubjectType.valueOf(subjectType));
            teacherService.add(name, familyName, newSubject, depId);
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
    public String deleteTeacher(@RequestParam("teacherId") int teacherId) {

        try {
            teacherService.deleteById(teacherId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/Teachers.jsp";
        }
    }

    @RequestMapping(value = "Teachers", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String findTeachers(@RequestParam(name = "userId", required = false) int userId,
                               Model model) {

        try {
            Department department = userService.getDepartment(userId);
            Set<Teacher> teachers = department.getTeachers();

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

        Iterable<Teacher> teachers;
        Department department;

        try {

            if (!"".equals(name) && !"".equals(familyName)) {
                teachers = teacherService.getTeacher(name, familyName);
            } else if (!"".equals(name)) {
                teachers = teacherService.getByName(name);
            } else if (!"".equals(familyName)) {
                teachers = teacherService.getByFamilyName(familyName);
            } else {
                teachers = teacherService.getAll();
            }

            Iterator<Teacher> iterator = teachers.iterator();
            department = iterator.next().getDepartment();

            model.addAttribute("department", department);
            model.addAttribute("teachers", teachers);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/Teachers.jsp";
        }
    }
}