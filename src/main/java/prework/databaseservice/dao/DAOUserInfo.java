package prework.databaseservice.dao;

import prework.data.Role;
import prework.data.UserInfo;

public interface DAOUserInfo {

    void add(UserInfo userInfo);

    void deleteByID(int userInfoID);

    void delelteByUsername(String username);

    void changePasswor(int userInfoID, String newPassword);

    void changeUsername(int userInfoID, String newUsername) throws Exception;

    void changeEnabled(int userInfoID, int newEnambled);

    void changeRole(int userInfoID, Role newRole);

    UserInfo getByID(int userInfoID);

    UserInfo getByUsername(String username);

}
