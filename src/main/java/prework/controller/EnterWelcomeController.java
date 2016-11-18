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
import prework.databaseservice.dao.DAOUser;

@Controller
public class EnterWelcomeController {

    @Autowired
    DAOUser daoUser;

    @RequestMapping(value = "/login")
    public String login() {

        return "./login.jsp";
    }

    @RequestMapping(value = "/jsp/welcome", method={RequestMethod.GET, RequestMethod.POST})
    public String welcome(@RequestParam(name = "user", required = false) User user,
                          Model model) {
        if(user == null){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            user = daoUser.getByUsername(auth.getName());
        }

        model.addAttribute("user", user);

        return "./welcome.jsp";
    }
}
