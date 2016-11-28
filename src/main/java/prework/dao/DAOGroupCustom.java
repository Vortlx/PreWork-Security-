package prework.dao;

import java.util.List;

import prework.entities.Group;
import prework.entities.Student;
import prework.entities.Subject;

public interface DAOGroupCustom {

    void addStudent(int groupID, Student student);

    void addSubject(int groupID, Subject subject);

    void deleteByName(String groupName);

    void deleteSubject(int groupId, int subjectId);

    List<Student> getStudents(int groupID);

    Student getStudent(int groupID, String studentName, String studentFamilyName);

    List<Subject> getSubjects(int groupID);

    Group getByName(String name);
}
