package prework.dao;


import java.sql.SQLException;
import java.util.List;

import prework.entities.Group;
import prework.entities.Student;
import prework.entities.User;

/**
 * This class define CRUD operation for students table.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 */
public interface DAOStudent {

    /**
     * This method add new student into students table. And this student will be contain in specific group.
     *
     * @param name       Name of student
     * @param familyName Family Name of student
     * @param groupName  Name of group in which this student
     * @return Nothing.
     * @throws SQLException
     */
    void add(Student student) throws SQLException;

    /**
     * This method update entities in students table.
     *
     * @param studentId     Id of student
     * @param newName       New name of student
     * @param newFamilyName New family name of student
     * @return Nothing.
     * @throws SQLException
     */
    void changeFullName(int studentId, String newName, String newFamilyName) throws SQLException;

    /**
     * Method change current group of student on new group
     *
     * @param studentId    Id of student
     * @param newGroupName Name of new group
     * @return Nothing
     * @throws SQLException
     */
    void changeGroup(int studentId, int newGroupId) throws SQLException;

    void deleteById(int studentId);

    /**
     * This method delete student with specific name and family name from students table.
     *
     * @param name       Name of student
     * @param familyName Family name of student
     * @return Nothing
     * @throws SQLException
     */
    void deleteByFullName(String name, String familyName) throws SQLException;

    Group getGroup(int studentId);

    Student getById(int studentId);

    /**
     * This method return list of all students.
     *
     * @return List of persons
     * @throws SQLException
     */
    List<Student> getAll() throws SQLException;

    /**
     * This method return list of all students who have a specific name.
     *
     * @param name Name of student for whom there is a search
     * @return List of students who have a specific name
     * @throws SQLException
     */
    List<Student> getByName(String name) throws SQLException;

    /**
     * This method return list of all students who have a specific family name.
     *
     * @param familyName Family name of student for whom there is a search
     * @return List of students who have a specific family name
     * @throws SQLException
     */
    List<Student> getByFamilyName(String familyName) throws SQLException;

    /**
     * Method return list of students who have specific name and specific family name
     *
     * @param name       Name of student
     * @param familyName Family name of student
     * @return List of students who have specific name and specific family name
     * @throws SQLException
     */
    List<Student> getStudent(String name, String familyName) throws SQLException;
}
