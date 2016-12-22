package prework.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import prework.entities.*;
import prework.service.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "jsp")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "AddStudent", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String addStudent(@RequestParam("name") String name, @RequestParam("familyName") String familyName,
                             @RequestParam("groupID") int groupId,
                             Model model) {
        try {
            studentService.add(name, familyName, groupId);
        } catch (Exception e) {
            e.printStackTrace();

            Group group = groupService.getById(groupId);

            String message = "Can't do this operation.";
            model.addAttribute("message", message);
            model.addAttribute("groups", group.getDepartment().getGroups());
            return "add/AddStudent.jsp";
        }

        return "../welcome";
    }

    @RequestMapping(value = "DeleteStudent", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String deleteStudent(@RequestParam("studentId") int studentId) {

        try {
            studentService.deleteById(studentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/Students.jsp";
        }
    }

    @RequestMapping(value = "ChangeGroupPage", method = RequestMethod.GET)
    public String changeGroupPage(@RequestParam("depId") int depId,
                                  @RequestParam("studentId") int studentId,
                                  Model model) {

        try {
            Department department = departmentService.getById(depId);

            model.addAttribute("groups", department.getGroups());
            model.addAttribute("studentId", studentId);
            model.addAttribute("userId", department.getUser().getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "update/ChangeGroup.jsp";
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

            model.addAttribute("userId", department.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "Students";
        }
    }

    @RequestMapping(value = "MyGroup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findMyGroup(@RequestParam(name = "userId", required = false) Integer userId,
                              @RequestParam(name = "groupId", required = false) Integer groupId) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String answer = null;
        try {
            if (groupId != null) {
                Group group = groupService.getById(groupId);
                answer = gson.toJson(group);
            } else {
                Student student = userService.getStudent(userId);
                answer = gson.toJson(student.getGroup());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

    @RequestMapping(value = "MySubjects", method = {RequestMethod.GET, RequestMethod.POST})
    public String findMySubjects(@RequestParam(name = "userId", required = false) int userId,
                                 @RequestParam(name = "groupId", required = false) Integer groupId,
                                 Model model) {

        try {
            if (groupId != null) {
                Group group = groupService.getById(groupId);

                model.addAttribute("subjects", group.getSubjects());
                model.addAttribute("userId", userId);
                model.addAttribute("groupId", groupId);
            } else {
                Student student = userService.getStudent(userId);

                model.addAttribute("subjects", student.getGroup().getSubjects());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/MySubjects.jsp";
        }
    }

    @RequestMapping(value = "Students", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String findStudents(@RequestParam(name = "userId", required = false) int userId,
                               Model model) {

        try {
            Department department = userService.getDepartment(userId);
            Set<Student> students = studentService.getAll(department);

            model.addAttribute("department", department);
            model.addAttribute("students", students);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("userId", userId);
            return "search/Students.jsp";
        }
    }

    @RequestMapping(value = "Students", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String findStudents(@RequestParam(name = "name", required = false) String name,
                               @RequestParam(name = "familyName", required = false) String familyName,
                               Model model) {

        Iterable<Student> students;

        try {
            if (!"".equals(name) && !"".equals(familyName)) {
                students = studentService.getStudent(name, familyName);
            } else if (!"".equals(name)) {
                students = studentService.getByName(name);
            } else if (!"".equals(familyName)) {
                students = studentService.getByFamilyName(familyName);
            } else {
                students = studentService.getAll();
            }

            Iterator<Student> iterator = students.iterator();
            Department department = iterator.next().getDepartment();

            model.addAttribute("department", department);
            model.addAttribute("students", students);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/Students.jsp";
        }
    }
}