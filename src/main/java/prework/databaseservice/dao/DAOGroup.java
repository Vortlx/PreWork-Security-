package prework.databaseservice.dao;


import java.sql.SQLException;
import java.util.List;
import prework.data.Group;
import prework.data.Student;
import prework.data.Subject;

/**
 * This class define CRUD operation for groups table in database.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public interface DAOGroup {

	/**
	 * This method add new group into groups table .
	 *
	 * @param name Name of new group.
	 * @throw SQLException
	 * @return Nothing
	 * */
	void add(String name) throws SQLException;

	void addSubject(int groupID, Subject subject);

	/**
	 * This method set new name of table.
	 *
	 * @param groupID ID of group
	 * @param newName New name of group
	 * @throw SQLException
	 * @return Nothing
	 * */
	void changeName(int groupID, String newName) throws SQLException;

	/**
	 * This method delete group with specific name from groups table.
	 *
	 * @param name Name of group which must be deleted.
	 * @throw SQLException
	 * @return Nothing
	 * */
	void deleteByID(int groupID) throws SQLException;

	void deleteByName(String groupName) throws SQLException;

	List<Student> getStudents(int groupID);

	Student getStudent(int groupID, String studentName, String studentFamilyName);

	List<Subject> getSubjects(int groupID);

	/**
	 * Return group which have specific name
	 *
	 * @param name Name of group
	 * @return Group
	 * */
	Group getByName(String name) throws SQLException;
	
	/**
	 * This method return list of all existing groups.
	 *
	 * @throw SQLException
	 * @return List of name (String) of all groups
	 * */
	List<Group> getAll() throws SQLException;
}
