package prework.dao;

import prework.entities.Role;
import prework.entities.User;

public interface DAOUserCustom {

    void delelteByUsername(String username);

    void changePassword(int userID, String newPassword);

    void changeUsername(int userID, String newUsername) throws Exception;

    void changeEnabled(int userID, int newEnambled);

    void changeRole(int userID, Role newRole);

    User getByUsername(String username);
}
