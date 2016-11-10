package prework.databaseservice.dao;


import java.sql.SQLException;
import java.util.List;

import prework.data.Group;
import prework.data.Student;

/**
 * This class define CRUD operation for students table.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public interface DAOStudent {

	/**
	 * This method add new student into students table. And this student will be contain in specific group.
	 *
	 *  @param name Name of student
	 *  @param familyName Family Name of student
	 *  @param groupName Name of group in which this student
	 *
	 *  @throws SQLException
	 *  @return Nothing.
	 * */
	void add(String name, String familyName, String groupName) throws SQLException;
	
	/**
	 * This method update data in students table.
	 * 
	 * @param studentID ID of student
	 * @param newName New name of student
	 * @param newFamilyName New family name of student
	 *
	 * @throws SQLException
	 * @return Nothing.
	 * */
	void changeName(int studentID, String newName, String newFamilyName) throws SQLException;

	void changeLogin(int studentID, String newLogin);

	void changePassword(int studentID, String newPassword);

	/**
	 * Method change current group of student on new group
	 *
	 * @param studentID ID of student
	 * @param newGroupName Name of new group
	 *
	 * @throws SQLException
	 * @return Nothing
	 * */
	void changeGroup(int studentID, String newGroupName) throws SQLException;

	/**
	 * This method delete student with specific name and family name from students table.
	 * 
	 * @param name Name of student
	 * @param familyName Family name of student
	 *
	 * @throws SQLException
	 * @return Nothing
	 * */
	void delete(String name, String familyName) throws SQLException;

	Group getGroup(int studentID);

	/**
	 * This method return list of all students.
	 * 
	 * @throws SQLException
	 * @return List of persons
	 * */
	List<Student> getAll() throws SQLException;

	/**
	 * This method return list of all students who have a specific name.
	 *
	 * @param name Name of student for whom there is a search
	 * @throws SQLException
	 * @return List of students who have a specific name
	 * */
	List<Student> getByName(String name) throws SQLException;

	/**
	 * This method return list of all students who have a specific family name.
	 * 
	 * @param familyName Family name of student for whom there is a search
	 * @throws SQLException
	 * @return List of students who have a specific family name
	 * */
	List<Student> getByFamilyName(String familyName) throws SQLException ;
	
	/**
	 * Method return list of students who have specific name and specific family name
	 *
	 * @param name Name of student
	 * @param familyName Family name of student
	 * @throws SQLException
	 * @return List of students who have specific name and specific family name
	 * */
	List<Student> getStudent(String name, String familyName) throws SQLException;
}
