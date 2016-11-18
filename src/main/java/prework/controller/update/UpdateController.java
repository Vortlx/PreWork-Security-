package prework.controller.update;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import prework.data.Department;
import prework.data.Student;
import prework.data.UserInfo;
import prework.databaseservice.dao.DAODepartment;
import prework.databaseservice.dao.DAOStudent;
import prework.databaseservice.dao.DAOUserInfo;

@Controller
@RequestMapping(value = "/jsp")
public class UpdateController {

    @Autowired
    private DAOUserInfo daoUserInfo;

    @Autowired
    private DAOStudent daoStudent;

    @Autowired
    private DAODepartment daoDepartment;

    @RequestMapping(value="/ChangePassword", method=RequestMethod.GET)
    public String toChangePasswordPage(@RequestParam("userId") int userId,
                                       Model model){
        model.addAttribute("userId", userId);
        return "./update/ChangePassword.jsp";
    }

    @RequestMapping(value="/ChangeUsername", method=RequestMethod.GET)
    public String toChangeUsernamePage(@RequestParam("userId") int userId,
                                       Model model){
        model.addAttribute("userId", userId);
        return "./update/ChangeUsername.jsp";
    }

    @RequestMapping(value = "/ChangePassword", method = RequestMethod.POST)
    public String changePasswor(@RequestParam("userId") String userId, @RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword, Model model) {

        int userIdInt = Integer.parseInt(userId);
        UserInfo userInfo = daoUserInfo.getByID(userIdInt);

        if (userInfo.getPassword().equals(oldPassword)) {
            daoUserInfo.changePassword(userIdInt, newPassword);
            model.addAttribute("userInfo", userInfo);
        } else {
            String message = "Passwords don't match";
            model.addAttribute("message", message);
            return "./update/ChangePassword.jsp";
        }

        return "./welcome";
    }

    @RequestMapping(value = "/ChangeUsername", method = RequestMethod.POST)
    public String changeUsername(@RequestParam("userId") int userId, @RequestParam("password") String password,
                                 @RequestParam("username") String username, Model model) {

        UserInfo userInfo = daoUserInfo.getByID(userId);
        String message = null;

        try {
            if (userInfo.getPassword().equals(password)) {
                daoUserInfo.changeUsername(userId, username);
                model.addAttribute("userInfo", daoUserInfo.getByID(userId));
            } else {
                message = "Wrong password";
                model.addAttribute("message", message);
                return "./ChangeUsername.jsp";
            }
        } catch (Exception e) {
            message = "User with that username exist";
            model.addAttribute("message", message);
            return "./update/ChangeUsername.jsp";
        }

        return "./welcome";
    }

    @RequestMapping(value = "/ChangeGroup", method = RequestMethod.POST)
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
            return "./Students";
        }
    }

    @RequestMapping(value = "/ChangeGroupPage", method = RequestMethod.GET)
    public String changeGroupPage(@RequestParam("depId") String depId,
                                  @RequestParam("studentId") String studentId,
                                  Model model) {

        try {
            Department department = daoDepartment.getByID(Integer.parseInt(depId));

            model.addAttribute("groups", department.getGroups());
            model.addAttribute("studentID", studentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "./update/ChangeGroup.jsp";
        }
    }
}
