package prework.databaseservice.dao;

import prework.entities.Role;
import prework.entities.User;

public interface DAOUser {

    void add(User user) throws Exception;

    void deleteByID(int userID);

    void delelteByUsername(String username);

    void changePassword(int userID, String newPassword);

    void changeUsername(int userID, String newUsername) throws Exception;

    void changeEnabled(int userID, int newEnambled);

    void changeRole(int userID, Role newRole);

    User getByID(int userID);

    User getByUsername(String username);

}
