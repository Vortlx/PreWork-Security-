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

    @RequestMapping(value = "ChangePassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam("userId") int userId, @RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword, Model model) {

        User user = userService.getById(userId);
        String message = "";

        if(user.checkPassword(oldPassword)) {
            userService.changePassword(user, newPassword);
            message = "Operation was success!";
        }else{
            message = "Passwords don't match";
        }
        
        model.addAttribute("message", message);
        return "ChangePassword.jsp";
    }
}
