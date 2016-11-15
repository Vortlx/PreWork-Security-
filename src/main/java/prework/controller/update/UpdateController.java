package prework.controller.update;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import prework.data.UserInfo;
import prework.databaseservice.dao.DAOUserInfo;

@Controller
@RequestMapping(value="/jsp/update")
public class UpdateController {

    @Autowired
    private DAOUserInfo daoUserInfo;

    @RequestMapping(value="/ChangePassword", method = RequestMethod.POST)
    public String changePasswor(@RequestParam("userId") String userId, @RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword, Model model){

        int userIdInt = Integer.parseInt(userId);
        UserInfo userInfo = daoUserInfo.getByID(userIdInt);

        if(userInfo.getPassword().equals(oldPassword)){
            daoUserInfo.changePassword(userIdInt, newPassword);
            model.addAttribute("userInfo", userInfo);
        }else{
            String message = "Passwords don't match";
            model.addAttribute("message", message);
            return "./ChangePassword";
        }

        return "../welcome";
    }

    @RequestMapping(value="/ChangeUsername", method = RequestMethod.POST)
    public String changeUsername(@RequestParam("userId") String userId, @RequestParam("password") String password,
                                @RequestParam("username") String username, Model model){

        int userIdInt = Integer.parseInt(userId);
        UserInfo userInfo = daoUserInfo.getByID(userIdInt);
        String message = null;

        try{
            if(userInfo.getPassword().equals(password)){
                daoUserInfo.changeUsername(userIdInt, username);
                model.addAttribute("userInfo", userInfo);
            }else{
                message = "Passwords don't match";
                model.addAttribute("message", message);
                return "./ChangeUsername";
            }
        }catch(Exception e){
            message = "User with that username exist";
            model.addAttribute("message", message);
            return "./ChangeUsername";
        }

        return "../welcome";
    }

    @RequestMapping(value="/ChangeGroup", method=RequestMethod.GET)
    public String changeGroupForStudent(@RequestParam("studentId") String studentId, Model model){



        return "";
    }
}
