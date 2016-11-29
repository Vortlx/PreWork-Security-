package prework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prework.dao.crudinterface.DAOUser;
import prework.entities.*;
import prework.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private DAOUser daoUser;

    @Transactional(rollbackFor = Exception.class)
    public User add(String name, String familyName, Role role) throws Exception{
        User newUser = new User(name, familyName, role);
        daoUser.save(newUser);

        return daoUser.getByUsername(familyName + name);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(int userId) {
        daoUser.delete(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(User user, String newPassword){
        daoUser.changePassword(user.getId(), newPassword);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changeUsername(User user, String username) throws Exception{
        daoUser.changeUsername(user.getId(), username);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public User getById(int userId) {
        return daoUser.findOne(userId);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public User getByUsername(String username) {
        return daoUser.getByUsername(username);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Student getStudent(int userId) {
        return getById(userId).getStudent();
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Teacher getTeacher(int userId) {
        return getById(userId).getTeacher();
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Department getDepartment(int userId) {
        return getById(userId).getDepartment();
    }
}
