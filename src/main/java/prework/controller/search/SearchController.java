package prework.controller.search;


import org.springframework.security.access.prepost.PreAuthorize;
import prework.entities.*;
import prework.dao.DAOGroup;
import prework.dao.DAOStudent;
import prework.dao.DAOTeacher;
import prework.dao.DAOUser;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/jsp")
public class SearchController {

    @Autowired
    private DAOGroup daoGroup;

    @Autowired
    private DAOStudent daoStudent;

    @Autowired
    private DAOTeacher daoTeacher;

    @Autowired
    private DAOUser daoUser;

    @RequestMapping(value = "/MyGroup", method = RequestMethod.GET)
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
            return "./search/MyGroup.jsp";
        }
    }

    @RequestMapping(value = "/MySubjects", method = {RequestMethod.GET, RequestMethod.POST})
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
            return "./search/MySubjects.jsp";
        }
    }

    @RequestMapping(value = "/Groups", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_DEPARTMENT', 'ROLE_TEACHER')")
    public String findGroups(@RequestParam("userId") String userId, Model model) {

        int userIdInt = Integer.parseInt(userId);
        Set<Group> groups = null;
        Department department = null;
        Teacher teacher = null;

        try {
            User user = daoUser.getByID(userIdInt);

            if (user.getDepartment() != null) {
                department = user.getDepartment();
                groups = department.getGroups();
            } else {
                teacher = user.getTeacher();
                groups = teacher.getSubject().getGroups();
            }

            model.addAttribute("userId", userIdInt);
            model.addAttribute("groups", groups);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("userId", userId);
            return "./search/Groups.jsp";
        }
    }

    // Need rewrite
    @RequestMapping(value = "/Students", method = RequestMethod.GET)
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
            return "./search/Students.jsp";
        }
    }

    @RequestMapping(value = "/Students", method = RequestMethod.POST)
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
            return "./search/Students.jsp";
        }
    }

    @RequestMapping(value = "/Teachers", method = RequestMethod.GET)
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
            return "./search/Teachers.jsp";
        }
    }

    @RequestMapping(value = "/Teachers", method = RequestMethod.POST)
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
            return "./search/Teachers.jsp";
        }
    }
}