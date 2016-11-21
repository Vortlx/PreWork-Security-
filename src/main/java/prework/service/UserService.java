package prework.service;

import prework.entities.*;


public interface UserService {

    User add(String name, String familyName, Role role) throws Exception;

    void deleteById(int userId);

    User changePassword(int userId, String oldPassword, String newPassword) throws Exception;

    User changeUsername(int userId, String password, String username) throws Exception;

    User getById(int userId);

    User getByUsername(String username);

    Student getStudent(int userId);

    Teacher getTeacher(int userId);

    Department getDepartment(int userId);
}
