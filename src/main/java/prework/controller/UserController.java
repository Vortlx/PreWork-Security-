package prework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import prework.dao.DAOUser;
import prework.entities.User;

@Controller
@RequestMapping(value = "jsp")
public class UserController {

    @Autowired
    private DAOUser daoUser;

    @RequestMapping(value="ChangePassword", method= RequestMethod.GET)
    public String toChangePasswordPage(@RequestParam("userId") int userId,
                                       Model model){
        model.addAttribute("userId", userId);
        return "update/ChangePassword.jsp";
    }

    @RequestMapping(value="ChangeUsername", method=RequestMethod.GET)
    public String toChangeUsernamePage(@RequestParam("userId") int userId,
                                       Model model){
        model.addAttribute("userId", userId);
        return "update/ChangeUsername.jsp";
    }

    @RequestMapping(value = "ChangePassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam("userId") String userId, @RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword, Model model) {

        int userIdInt = Integer.parseInt(userId);
        User user = daoUser.getByID(userIdInt);

        if (user.getPassword().equals(oldPassword)) {
            daoUser.changePassword(userIdInt, newPassword);
            model.addAttribute("user", user);
        } else {
            String message = "Passwords don't match";
            model.addAttribute("message", message);
            return "update/ChangePassword.jsp";
        }

        return "welcome";
    }

    @RequestMapping(value = "ChangeUsername", method = RequestMethod.POST)
    public String changeUsername(@RequestParam("userId") int userId, @RequestParam("password") String password,
                                 @RequestParam("username") String username, Model model) {

        User user = daoUser.getByID(userId);
        String message = null;

        try {
            if (user.getPassword().equals(password)) {
                daoUser.changeUsername(userId, username);

                System.out.println();
                System.out.println(user);
                System.out.println();
                model.addAttribute("user", user);
            } else {
                message = "Wrong password";
                model.addAttribute("message", message);
                return "update/ChangeUsername.jsp";
            }
        } catch (Exception e) {
            message = "User with that username exist";
            model.addAttribute("message", message);
            return "update/ChangeUsername.jsp";
        }

        return "welcome";
    }
}
