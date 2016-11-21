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
public class GroupController {

    @Autowired
    private DAOGroup daoGroup;

    @Autowired
    private DAOUser daoUser;

    @Autowired
    private DAODepartment daoDepartment;

    @Autowired
    private DAOSubject daoSubject;

    @RequestMapping(value = "AddGroup", method = RequestMethod.POST)
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

            return "add/AddGroup.jsp";
        }

        return "welcome";
    }

    @RequestMapping(value = "AddSubjectPage", method = RequestMethod.GET)
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

        return "add/AddSubject.jsp";
    }

    @RequestMapping(value = "AddSubject", method = RequestMethod.POST)
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

            return "add/AddSubject.jsp";
        } finally {
            model.addAttribute("groupId", groupId);
            model.addAttribute("userId", userId);
        }

        return "MySubjects";
    }

    @RequestMapping(value = "DeleteGroup", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String deleteGroup(@RequestParam("groupId") int groupId,
                              @RequestParam("userId") int userId,
                              Model model) {

        try {
            Group group = daoGroup.getByID(groupId);
            for (Student student : group.getStudents()) {
                daoUser.deleteByID(student.getUser().getId());
            }

            daoGroup.deleteByID(groupId);
            model.addAttribute("userId", userId);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            return "Groups";
        }
    }

    @RequestMapping(value = "DeleteSubject", method = RequestMethod.GET)
    public String deleteSubjectFromGroup(@RequestParam("groupId") int groupId,
                                         @RequestParam("subjectId") int subjectId,
                                         @RequestParam("userId") int userId,
                                         Model model) {
        try {
            daoGroup.deleteSubject(groupId, subjectId);

            model.addAttribute("groupId", groupId);
            model.addAttribute("userId", userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "MySubjects";
    }

    @RequestMapping(value = "Groups", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_DEPARTMENT', 'ROLE_TEACHER')")
    public String findGroups(@RequestParam("userId") int userId, Model model) {

        Set<Group> groups = null;
        Department department = null;
        Teacher teacher = null;

        try {
            User user = daoUser.getByID(userId);

            if (user.getDepartment() != null) {
                department = user.getDepartment();
                groups = department.getGroups();
            } else {
                teacher = user.getTeacher();
                groups = teacher.getSubject().getGroups();
            }

            model.addAttribute("groups", groups);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("userId", userId);
            return "search/Groups.jsp";
        }
    }
}
