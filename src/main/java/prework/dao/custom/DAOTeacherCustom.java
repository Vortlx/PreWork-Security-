package prework.dao.custom;

import java.sql.SQLException;
import java.util.List;

import prework.entities.Subject;
import prework.entities.Teacher;

public interface DAOTeacherCustom {

    void changeFullName(int teacherId, String newName, String newFamilyName) throws SQLException;

    void deleteByFullName(String name, String familyName) throws SQLException;

    Subject getSubject(int teacherId);

    List<Teacher> getByName(String name) throws SQLException;

    List<Teacher> getByFamilyName(String familyName) throws SQLException;

    List<Teacher> getTeacher(String name, String familyName) throws SQLException;
}
