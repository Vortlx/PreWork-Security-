package prework.databaseservice.dao;

import prework.data.Group;
import prework.data.Subject;
import prework.data.SubjectType;
import prework.data.Teacher;

import java.util.List;
import java.util.Set;

public interface DAOSubject {

    void add(Subject subject);

    void changeName(int subjectID, String newName);

    void delete(int subjectID);

    Subject getById(int subjectId);
    
    Subject getByNameAndType(String name, SubjectType subjectType);

    Set<Subject> getAll();

    List<Group> getGroups(int subjectID);

    Group getGroup(int subjectID, String groupName);

    List<Teacher> getTeachers(int subjectID);

    Teacher getTeacher(int subjectID, String teacherName, String teacherFamilyName);
}
