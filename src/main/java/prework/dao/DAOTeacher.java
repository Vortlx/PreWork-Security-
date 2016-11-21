package prework.dao;


import java.sql.SQLException;
import java.util.List;

import prework.entities.Department;
import prework.entities.Subject;
import prework.entities.Teacher;
import prework.entities.User;

/**
 * This class define CRUD operation for teachers table.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 */
public interface DAOTeacher {

    /**
     * This method add new teacher into teachers table.
     *
     * @param name       Name of teacher
     * @param familyName Family name of teacher
     * @param groups     Array of groups which under this teacher
     * @return Nothing.
     * @throws SQLException
     */
    void add(Teacher teacher) throws SQLException;

    /**
     * This method update entities into teachers table.
     *
     * @param teacherId     Id of teacher
     * @param newName       New name of teacher
     * @param newFamilyName New family name of teacher
     * @return Nothing.
     * @throws SQLException
     */
    void changeFullName(int teacherId, String newName, String newFamilyName) throws SQLException;

    void deleteById(int teacherId);

    /**
     * This method delete teacher with specific name and family name from teachers table.
     *
     * @param name       Name of teacher
     * @param familyName Family name of teacher
     * @return Nothing
     * @throws SQLException
     */
    void deleteByFullName(String name, String familyName) throws SQLException;

    Subject getSubject(int teacherId);

    Teacher getById(int teacherId);

    /**
     * This method return list of all teachers who have a specific name.
     *
     * @param name Name of teacher for whom there is a search
     * @return List of teachers who have a specific name
     * @throws SQLException
     */
    List<Teacher> getByName(String name) throws SQLException;

    /**
     * This method return list of all teachers who have a specific family name.
     *
     * @param familyName Family name of teacher for whom there is a search
     * @return List of teachers who have a specific family name
     * @throws SQLException
     */
    List<Teacher> getByFamilyName(String familyName) throws SQLException;

    /**
     * Method return list of teachers who have specific name and specific family name
     *
     * @param name       Name of teacher
     * @param familyName Family name of teachers
     * @return List of teachers who have specific name and specific family name
     * @throws SQLException
     */
    List<Teacher> getTeacher(String name, String familyName) throws SQLException;

    /**
     * This method return list of all teachers.
     *
     * @return List of teachers
     * @throws SQLException
     */
    List<Teacher> getAll() throws SQLException;
}
