package prework.dao;

import prework.entities.Department;
import prework.entities.Group;
import prework.entities.Teacher;

import java.util.List;

public interface DAODepartment {

    void add(Department department);

    void addGroup(int depId, Group group);

    void changeName(int depId, String newName);

    void delete(int depId);

    Department getById(int depId);

    Department getByName(String name);

    List<Group> getGroups(int depId);

    Group getGroup(int depId, String groupName);

    List<Teacher> getTeachers(int depId);

    Teacher getTeacher(int depId, String teacherName, String teacherFamilyName);
}