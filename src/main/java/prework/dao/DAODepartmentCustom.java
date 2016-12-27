package prework.dao;

import java.util.List;
import java.util.Set;

import prework.entities.Department;
import prework.entities.Group;
import prework.entities.Student;
import prework.entities.Teacher;

public interface DAODepartmentCustom {

    void addGroup(int depId, Group group);

    void changeName(int depId, String newName);

    Department getByName(String name);

    List<Group> getGroups(int depId);

    Group getGroup(int depId, String groupName);

    Teacher getTeacher(int depId, String teacherName, String teacherFamilyName);
}
