package prework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prework.dao.crudinterface.DAOUser;
import prework.entities.*;
import prework.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private DAOUser daoUser;

    public User add(String name, String familyName, Role role) throws Exception{
        User newUser = new User(name, familyName, role);
        daoUser.save(newUser);

        return daoUser.getByUsername(familyName + name);
    }

    public void deleteById(int userId) {
        daoUser.delete(userId);
    }

    public void changePassword(User user, String newPassword){
        daoUser.changePassword(user.getId(), newPassword);
    }

    public void changeUsername(User user, String username) throws Exception{
        daoUser.changeUsername(user.getId(), username);
    }

    public User getById(int userId) {
        return daoUser.findOne(userId);
    }

    public User getByUsername(String username) {
        return daoUser.getByUsername(username);
    }

    public Student getStudent(int userId) {
        return getById(userId).getStudent();
    }

    public Teacher getTeacher(int userId) {
        return getById(userId).getTeacher();
    }

    public Department getDepartment(int userId) {
        return getById(userId).getDepartment();
    }
}
