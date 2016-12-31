package prework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import prework.entities.*;
import prework.service.GroupService;
import prework.service.SubjectService;
import prework.service.UserService;

@Controller
@RequestMapping(value = "jsp")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private SubjectService subjectService;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add/AddGroup", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String addGroup(@RequestParam("groupName") String groupName,
                           @RequestParam("userId") int userId,
                           Model model) {
        User user = userService.getById(userId);
        Department dep = user.getDepartment();
        String message = "";
        
        try {
            groupService.add(groupName, dep.getId());
            message = "Operation was success!";
        } catch (Exception e) {
            e.printStackTrace();
            message = "Group with specified name already exist!";
        } finally {
            model.addAttribute("message", message);
            model.addAttribute("userId", userId);
        }

        return "AddGroup.jsp";
    }

    @RequestMapping(value = "AddSubjectPage", method = RequestMethod.GET)
    public String addSubjectPage(@RequestParam("groupId") int groupId,
                                 @RequestParam("userId") int userId,
                                 @RequestParam(name = "page", required = false) Integer page,
                                 Model model) {
        if(page == null){
            page = 1;
        }

        try {
            Iterable<Subject> subjects = subjectService.getAll();

            model.addAttribute("subjects", subjects);
            model.addAttribute("groupId", groupId);
            model.addAttribute("userId", userId);
            model.addAttribute("page", page);
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
                             @RequestParam(name = "page", required = false) Integer page,
                             Model model) {

        if(page == null){
            page = 1;
        }

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
            model.addAttribute("page", page);
        }

        return "MySubjects";
    }

    @RequestMapping(value = "DeleteGroup", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String deleteGroup(@RequestParam("groupId") int groupId,
                              @RequestParam("userId") int userId,
                              @RequestParam(name = "page", required = false) Integer page,
                              Model model) {

        if(page == null){
            page = 1;
        }

        try {
            groupService.deleteById(groupId);

            model.addAttribute("userId", userId);
            model.addAttribute("page", page);
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
                                         @RequestParam(name = "page", required = false) Integer page,
                                         Model model) {
        if(page == null){
            page = 1;
        }

        try {
            groupService.deleteSubject(groupId, subjectId);

            model.addAttribute("groupId", groupId);
            model.addAttribute("userId", userId);
            model.addAttribute("page", page);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "MySubjects";
    }

    @RequestMapping(value = "Groups", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_DEPARTMENT', 'ROLE_TEACHER')")
    public String findGroups(@RequestParam("userId") int userId,
                             @RequestParam(name = "page", required = false) Integer page, Model model) {

        if(page == null){
            page = 1;
        }

        try {
            User user = userService.getById(userId);
            Page<Group> groups = null;
            if(user.getDepartment() != null){
                Department department = user.getDepartment();
                groups = groupService.getByDepartmentId(department.getId(), page);
            } else{
                Teacher teacher = user.getTeacher();
                groups = groupService.getBySubjectsTeacherId(teacher.getId(), page);
            }

            model.addAttribute("groups", groups.getContent());
            model.addAttribute("page", page);
            model.addAttribute("maxPage", groups.getTotalPages());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("userId", userId);
        }
        return "search/Groups.jsp";
    }
}