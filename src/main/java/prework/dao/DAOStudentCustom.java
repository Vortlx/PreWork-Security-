package prework.dao;

import java.sql.SQLException;
import java.util.List;

import prework.entities.Group;
import prework.entities.Student;

public interface DAOStudentCustom {
    
    void changeFullName(int studentId, String newName, String newFamilyName) throws SQLException;

    void changeGroup(int studentId, int newGroupId) throws SQLException;

    void deleteByFullName(String name, String familyName) throws SQLException;

    Group getGroup(int studentId);

    List<Student> getByName(String name) throws SQLException;

    List<Student> getByFamilyName(String familyName) throws SQLException;

    List<Student> getByNameAndFamilyName(String name, String familyName) throws SQLException;
}
