package prework.dao;

import prework.entities.Role;
import prework.entities.User;

public interface DAOUser {

    void add(User user) throws Exception;

    void deleteById(int userId);

    void delelteByUsername(String username);

    void changePassword(int userID, String newPassword);

    void changeUsername(int userID, String newUsername) throws Exception;

    void changeEnabled(int userID, int newEnambled);

    void changeRole(int userID, Role newRole);

    User getById(int userId);

    User getByUsername(String username);

}
