package prework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import prework.data.UserInfo;
import prework.databaseservice.dao.DAOUserInfo;

@Controller
public class EnterWelcomeController {

    @Autowired
    DAOUserInfo daoUserInfo;

    @RequestMapping(value="/login")
    public String login(){

        return "./login";
    }

    @RequestMapping(value="/jsp/welcome")
    public String welcome(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = daoUserInfo.getByUsername(auth.getName());
        model.addAttribute("userInfo", userInfo);

        return "./welcome";
    }
}
