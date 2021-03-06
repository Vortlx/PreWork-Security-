package prework.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public String addTeacher(@RequestParam("personName") String name, @RequestParam("personFamilyName") String familyName,
                             @RequestParam("subjectName") String subjectName,
                             @RequestParam("subjectType") String subjectType,
                             @RequestParam("userId") int userId,
                             Model model) {
        
        User user = userService.getById(userId);
        Department dep = user.getDepartment();
        String message = "";
        try {
            Subject newSubject = subjectService.add(subjectName, SubjectType.valueOf(subjectType));
            teacherService.add(name, familyName, newSubject, dep.getId());
            
            message = "Operation was success!";
        } catch (Exception e) {
            e.printStackTrace();
            message = "That user already exist!";

        } finally {
            model.addAttribute("message", message);
            model.addAttribute("userId", userId);
        }

        return "AddTeacher.jsp";
    }

    @RequestMapping(value = "DeleteTeacher", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String deleteTeacher(@RequestParam("teacherId") int teacherId,
                                @RequestParam("userId") int userId,
                                @RequestParam(name="page", required = false) Integer page, Model model ) {

        if(page == null){
            page = 1;
        }

        try {
            teacherService.deleteById(teacherId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("userId", userId);
            model.addAttribute("page", page);
        }
        return "Teachers.jsp";
    }

    @RequestMapping(value = "Teachers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    @ResponseBody
    public String findTeachers(@RequestParam(name = "userId", required = false) int userId,
                                @RequestParam(name="page", required = false) Integer page) {

        if(page == null){
            page = 1;
        }

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String answer = "";
        try {
            Department department = userService.getDepartment(userId);
            Page<Teacher> teachers = teacherService.getByDepartmentId(department.getId(), page);

            answer = gson.toJson(teachers.getContent());
            answer = "{\"teachers\":" + answer + ", \"maxPage\":" +
                        teachers.getTotalPages() + ", \"page\":" + page + "}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }
}