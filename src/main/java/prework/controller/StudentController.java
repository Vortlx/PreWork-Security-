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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "jsp")
public class StudentController {

    @Autowired
    private DAOStudent daoStudent;

    @Autowired
    private DAOUser daoUser;

    @Autowired
    private DAODepartment daoDepartment;

    @Autowired
    private DAOGroup daoGroup;

    @Autowired
    private DAORole daoRole;

    @RequestMapping(value = "AddStudent", method = RequestMethod.POST)
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

            return "add/AddStudent.jsp";
        }

        return "welcome";
    }

    @RequestMapping(value = "DeleteStudent", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String deleteStudent(@RequestParam("studentId") int studentId, Model model) {

        try {
            Student student = daoStudent.getById(studentId);

            daoUser.deleteByID(student.getUser().getId());
            daoStudent.deleteByID(studentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/Students.jsp";
        }
    }

    @RequestMapping(value = "ChangeGroupPage", method = RequestMethod.GET)
    public String changeGroupPage(@RequestParam("depId") int depId,
                                  @RequestParam("studentId") String studentId,
                                  @RequestParam("userId") int userId,
                                  Model model) {

        try {
            Department department = daoDepartment.getByID(depId);

            model.addAttribute("groups", department.getGroups());
            model.addAttribute("studentID", studentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("userId", userId);
            return "update/ChangeGroup.jsp";
        }
    }

    @RequestMapping(value = "ChangeGroup", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String changeGroupForStudent(@RequestParam("studentID") String studentId,
                                        @RequestParam("newGroupId") String newGroupId,
                                        Model model) {
        try {
            Student student = daoStudent.getById(Integer.parseInt(studentId));
            Department department = student.getGroup().getDepartment();
            daoStudent.changeGroup(student.getId(), Integer.parseInt(newGroupId));

            model.addAttribute("userId", department.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "Students";
        }
    }

    @RequestMapping(value = "MyGroup", method = RequestMethod.GET)
    public String findMyGroup(@RequestParam(name = "userId", required = false) String userId,
                              @RequestParam(name = "groupId", required = false) String groupId, Model model) {

        try {
            if (groupId != null) {
                Group group = daoGroup.getByID(Integer.parseInt(groupId));

                model.addAttribute("group", group);

            } else {
                User user = daoUser.getByID(Integer.parseInt(userId));
                Student student = user.getStudent();

                model.addAttribute("group", student.getGroup());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/MyGroup.jsp";
        }
    }

    @RequestMapping(value = "MySubjects", method = {RequestMethod.GET, RequestMethod.POST})
    public String findMySubjects(@RequestParam(name = "userId", required = false) int userId,
                                 @RequestParam(name = "groupId", required = false) String groupId,
                                 Model model) {

        try {
            if (groupId != null) {
                Group group = daoGroup.getByID(Integer.parseInt(groupId));

                model.addAttribute("subjects", group.getSubjects());
                model.addAttribute("userId", userId);
                model.addAttribute("groupId", groupId);
            } else {
                User user = daoUser.getByID(userId);
                Student student = user.getStudent();

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
    public String findStudents(@RequestParam(name = "userId", required = false) String userId,
                               Model model) {

        int userIdInt = Integer.parseInt(userId);
        Set<Student> students;
        Department department;

        try {

            User user = daoUser.getByID(userIdInt);
            department = user.getDepartment();

            students = new HashSet<Student>();
            for (Group group : department.getGroups()) {
                students.addAll(group.getStudents());
            }

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

        List<Student> students;
        Department department;

        try {
            if (!"".equals(name) && !"".equals(familyName)) {
                students = daoStudent.getStudent(name, familyName);
            } else if (!"".equals(name)) {
                students = daoStudent.getByName(name);
            } else if (!"".equals(familyName)) {
                students = daoStudent.getByFamilyName(familyName);
            } else {
                students = daoStudent.getAll();
            }

            Group group = students.get(0).getGroup();
            department = group.getDepartment();

            model.addAttribute("department", department);
            model.addAttribute("students", students);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/Students.jsp";
        }
    }
}
