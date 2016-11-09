package prework.databaseservice.dao;

import prework.data.Group;
import prework.data.Subject;
import prework.data.Teacher;

import java.util.List;

public interface DAOSubject {

    void add(Subject subject);

    void changeName(int subjectID, String newName);

    void delete(int subjectID);

    List<Group> getGroups(int subjectID);

    Group getGroup(int subjectID, String groupName);

    List<Teacher> getTeachers(int subjectID);

    Teacher getTeacher(int subjectID, String teacherName, String teacherFamilyName);
}
