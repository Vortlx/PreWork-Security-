package prework.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import prework.dao.DAOUser;
import prework.entities.Department;
import prework.entities.User;

@Controller
@RequestMapping(value = "jsp")
public class AddController {

    @Autowired
    private DAOUser daoUser;

    /**
     * Redirect query to specific Add* controller (AddGroup and so on)
     * */
    @RequestMapping(value = "Add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String add(@RequestParam("whatAdd") String whatAdd,
                      @RequestParam("userId") String userId, Model model) {

        User user = daoUser.getByID(Integer.parseInt(userId));
        Department department = user.getDepartment();

        if ("GROUP".equals(whatAdd)) {
            model.addAttribute("departmentId", department.getId());
            return "add/AddGroup.jsp";
        } else if ("STUDENT".equals(whatAdd)) {
            model.addAttribute("groups", department.getGroups());
            return "add/AddStudent.jsp";
        } else if ("TEACHER".equals(whatAdd)) {
            model.addAttribute("departmentId", department.getId());
            return "add/AddTeacher.jsp";
        } else {
            return "welcome";
        }
    }
}