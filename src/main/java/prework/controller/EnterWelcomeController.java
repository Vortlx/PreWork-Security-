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

    @RequestMapping(value = "/login")
    public String login() {

        return "./login.jsp";
    }

    @RequestMapping(value = "/jsp/welcome")
    public String welcome(@RequestParam(name = "userInfo", required = false) UserInfo userInfo,
                          Model model) {
        if(userInfo == null){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userInfo = daoUserInfo.getByUsername(auth.getName());
        }

        model.addAttribute("userInfo", userInfo);

        return "./welcome.jsp";
    }
}
