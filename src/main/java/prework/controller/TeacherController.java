package prework.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import prework.entities.*;
import prework.service.*;

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

        return "../welcome";
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

    @RequestMapping(value = "Teachers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    @ResponseBody
    public String findTeachers(@RequestParam(name = "userId", required = false) int userId) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String answer = "";
        try {
            Department department = userService.getDepartment(userId);
            Set<Teacher> teachers = department.getTeachers();

            answer = gson.toJson(teachers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }
}