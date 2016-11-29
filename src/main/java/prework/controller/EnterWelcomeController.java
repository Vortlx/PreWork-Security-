package prework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import prework.entities.User;
import prework.service.UserService;

@Controller
public class EnterWelcomeController {

    @Autowired
    private UserService userService;

//    @RequestMapping(value = "login")
//    public String login() {
//
//        return "login.jsp";
//    }

    @RequestMapping(value = "jsp/welcome", method={RequestMethod.GET, RequestMethod.POST})
    public String welcome(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        model.addAttribute("user", user);

        return "welcome.jsp";
    }
}