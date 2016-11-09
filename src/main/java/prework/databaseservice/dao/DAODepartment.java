package prework.databaseservice.dao;

import prework.data.Department;
import prework.data.Group;
import prework.data.Teacher;

import java.util.List;

public interface DAODepartment {

    void add(Department department);

    void changeName(int depID, String newName);

    void changeLogin(int depID, String newLogin);

    void changePassword(int depID, String newPassword);

    void delete(int depID);

    Department getBiID(int depID);

    Department getByName(String name);

    List<Group> getGroups(int depID);

    Group getGroup(int depID, String groupName);

    List<Teacher> getTeachers(int depID);

    Teacher getTeacher(int depID, String teacherName, String teacherFamilyName);
}