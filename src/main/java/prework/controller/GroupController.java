package prework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import prework.entities.*;
import prework.service.GroupService;
import prework.service.SubjectService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "jsp")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "AddGroup", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String addGroup(@RequestParam("name") String groupName,
                           @RequestParam("departmentId") int depId,
                           Model model) {
        try {
            groupService.add(groupName, depId);
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
            List<Subject> subjects = subjectService.getAll();

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
            subjectService.addGroup(subjectName, subjectType, groupId);
        } catch (Exception e) {
            e.printStackTrace();

            String message = "Can't do this operation.";

            model.addAttribute("message", message);
            model.addAttribute("subjects", subjectService.getAll());

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
            groupService.deleteById(groupId);

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
            groupService.deleteSubject(groupId, subjectId);

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

        try {
            Set<Group> groups = groupService.getAll(userId);

            model.addAttribute("groups", groups);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("userId", userId);
            return "search/Groups.jsp";
        }
    }
}