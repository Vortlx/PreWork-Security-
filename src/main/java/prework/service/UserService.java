package prework.service;

import prework.entities.*;


public interface UserService {

    User add(String name, String familyName, Role role) throws Exception;

    void deleteById(int userId);

    void changePassword(User user, String newPassword);

    void changeUsername(User user, String username) throws Exception;

    User getById(int userId);

    User getByUsername(String username);

    Student getStudent(int userId);

    Teacher getTeacher(int userId);

    Department getDepartment(int userId);
}
