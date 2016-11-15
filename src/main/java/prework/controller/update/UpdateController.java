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

    @RequestMapping(value="/jsp/update/ChangePassword", method = RequestMethod.POST)
    public String changePasswor(@RequestParam("id") int id, @RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword, Model model){

        UserInfo userInfo = daoUserInfo.getByID(id);

        if(userInfo.getPassword().equals(oldPassword)){
            daoUserInfo.changePassword(id, newPassword);
        }else{
            String message = "Passwords don't match";
            model.addAttribute("message", message);
            return "./ChangePassword";
        }

        return "../welcome";
    }

    @RequestMapping(value="/jsp/update/Changeusername", method = RequestMethod.POST)
    public String changeUsername(@RequestParam("id") int id, @RequestParam("password") String password,
                                @RequestParam("username") String username, Model model){

        UserInfo userInfo = daoUserInfo.getByID(id);
        String message = null;

        try{
            if(userInfo.getPassword().equals(password)){
                daoUserInfo.changeUsername(id, username);
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
}
