package prework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import prework.entities.User;
import prework.service.UserService;

@Controller
@RequestMapping(value = "jsp")
public class UserController {

    @Autowired
    private UserService userService;

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
    public String changePassword(@RequestParam("userId") int userId, @RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword, Model model) {

        User user = userService.getById(userId);

        if(user.checkPassword(oldPassword)) {
            userService.changePassword(user, newPassword);
            return "welcome";
        }else{
            String message = "Passwords don't match";
            model.addAttribute("message", message);
            return "update/ChangePassword.jsp";
        }
    }

    @RequestMapping(value = "ChangeUsername", method = RequestMethod.POST)
    public String changeUsername(@RequestParam("userId") int userId, @RequestParam("password") String password,
                                 @RequestParam("username") String username, Model model) {

        try {
            User user = userService.getById(userId);

            if(user.checkPassword(password)){
                userService.changeUsername(user, username);
            }else{
                throw new Exception();
            }
        } catch (Exception e) {
            String message = "This user exist or Wrong password";
            model.addAttribute("message", message);
            return "update/ChangeUsername.jsp";
        }

        return "welcome";
    }
}
