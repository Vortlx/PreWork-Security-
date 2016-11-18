package prework.databaseservice.dao;


import java.util.List;

import prework.entities.Department;
import prework.entities.Group;
import prework.entities.Student;
import prework.entities.Subject;

/**
 * This class define CRUD operation for groups table in database.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 */
public interface DAOGroup {

    /**
     * This method add new group into groups table .
     *
     * @param name Name of new group.
     * @return Nothing
     * @throw SQLException
     */
    void add(String name, Department department);

    void addStudent(int groupID, Student student);

    void addSubject(int groupID, Subject subject);

    /**
     * This method delete group with specific name from groups table.
     *
     * @param name Name of group which must be deleted.
     * @return Nothing
     * @throw SQLException
     */
    void deleteByID(int groupID);

    void deleteByName(String groupName);

    void deleteSubject(int groupId, int subjectId);

    List<Student> getStudents(int groupID);

    Student getStudent(int groupID, String studentName, String studentFamilyName);

    List<Subject> getSubjects(int groupID);

    Group getByID(int groupID);

    /**
     * Return group which have specific name
     *
     * @param name Name of group
     * @return Group
     */
    Group getByName(String name);

    /**
     * This method return list of all existing groups.
     *
     * @return List of name (String) of all groups
     * @throw SQLException
     */
    List<Group> getAll();
}
