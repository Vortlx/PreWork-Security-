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
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;
    
    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "AddStudentPage", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String addStudentPage(@RequestParam("userId") int userId,
                                Model model) {

        User user = userService.getById(userId);
        Department department = user.getDepartment();

        model.addAttribute("groups", department.getGroups());
        model.addAttribute("userId", userId);
        return "AddStudent.jsp";
    }

    @RequestMapping(value = "AddStudent", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String addStudent(@RequestParam("personName") String name, @RequestParam("personFamilyName") String familyName,
                             @RequestParam("groupId") int groupId,
                             Model model) {


        String message = "";
        try {
            studentService.add(name, familyName, groupId);
            message = "Operation was success!";
        } catch (Exception e) {
            e.printStackTrace();
            message = "Can't do this operation.";

        } finally{
            Group group = groupService.getById(groupId);

            model.addAttribute("message", message);
            model.addAttribute("groups", group.getDepartment().getGroups());
        }

        return "AddStudent.jsp";
    }

    @RequestMapping(value = "DeleteStudent", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String deleteStudent(@RequestParam("studentId") int studentId,
                                @RequestParam("userId") int userId,
                                @RequestParam(name="page", required = false) Integer page, Model model) {

        if(page == null){
            page = 1;
        }

        try {
            studentService.deleteById(studentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("userId", userId);
            model.addAttribute("page", page);
        }
        return "Students.jsp";
    }

    @RequestMapping(value = "ChangeGroupPage", method = RequestMethod.GET)
    public String changeGroupPage(@RequestParam("userId") int userId,
                                  @RequestParam("studentId") int studentId,
                                  Model model) {

        try {
            User user = userService.getById(userId);
            Department department = user.getDepartment();

            model.addAttribute("groups", department.getGroups());
            model.addAttribute("studentId", studentId);
            model.addAttribute("userId", department.getUser().getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "ChangeGroup.jsp";
        }
    }

    @RequestMapping(value = "ChangeGroup", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String changeGroupForStudent(@RequestParam("studentId") int studentId,
                                        @RequestParam("newGroupId") int newGroupId,
                                        Model model) {
        try {
            Student student = studentService.getById(studentId);
            Department department = student.getDepartment();
            studentService.changeGroup(student.getId(), newGroupId);

            model.addAttribute("userId", department.getUser().getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "Students.jsp";
        }
    }

    @RequestMapping(value = "MyGroup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findMyGroup(@RequestParam(name = "userId", required = false) Integer userId,
                              @RequestParam(name = "groupId", required = false) Integer groupId,
                              @RequestParam(name="page", required = false) Integer page) {

        if(page == null){
            page = 1;
        }

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String answer = "";
        try {
            if (groupId != null) {
                answer = gson.toJson(studentService.getByGroupId(groupId, page).getContent());

            } else {
                Student student = userService.getStudent(userId);
                answer = gson.toJson(studentService.getByGroupId(student.getGroup().getId(), page).getContent());
            }

            answer = "{\"students\":" + answer + "}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

    @RequestMapping(value = "MySubjects", method = {RequestMethod.GET, RequestMethod.POST})
    public String findMySubjects(@RequestParam(name = "userId", required = false) int userId,
                                 @RequestParam(name = "groupId", required = false) Integer groupId,
                                 @RequestParam(name = "page", required = false) Integer page,
                                 Model model) {
        if(page == null){
            page = 1;
        }

        Page<Subject> subjects = null;
        try {
            if (groupId != null) {

                subjects = subjectService.getByGroupsId(groupId, page);
                model.addAttribute("subjects", subjects.getContent());
                model.addAttribute("userId", userId);
                model.addAttribute("groupId", groupId);
            } else {
                Student student = userService.getStudent(userId);
                subjects = subjectService.getByGroupsId(student.getGroup().getId(), page);
                model.addAttribute("subjects", subjects.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("maxPage", subjects.getTotalPages());
            model.addAttribute("page", page);
            return "MySubjects.jsp";
        }
    }

    @RequestMapping(value = "Students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    @ResponseBody
    public String findStudents(@RequestParam(name = "userId", required = false) int userId,
                                @RequestParam(name = "page", required = false) Integer page) {

        if(page == null){
            page = 1;
        }

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String answer = "";
        try {
            Department department = userService.getDepartment(userId);
            Page<Student> students = studentService.getByGroupDepartmentId(department.getId(), page);

            answer = gson.toJson(students.getContent());
            answer = "{\"students\":" + answer + ", \"maxPage\":" +
                        students.getTotalPages() + ", \"page\":" + page + "}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }
}