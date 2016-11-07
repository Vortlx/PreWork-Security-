package prework.databaseservice.dao;


import java.sql.SQLException;
import java.util.List;

import prework.data.Teacher;

/**
 * This class define CRUD operation for teachers table.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public interface DAOTeacher {
	
	/**
	 * This method add new teacher into teachers table.
	 *
	 * @param name Name of teacher
	 * @param familyName Family name of teacher
	 * @param groups Array of groups which under this teacher
	 *
	 *  @throws SQLException
	 *  @return Nothing.
	 * */
	public void add(String name, String familyName) throws SQLException;


	/**
	 * Method make teacher with specific name and family name curator of group with specific name
	 *
	 * @param teacherID ID of teacher
	 * @param groupName Name of group
	 *
	 * @throws SQLException
	 * @return Nothing
	 * */
	public void addGroup(int teacherID, String groupName) throws SQLException;
	
	/**
	 * This method update data into teachers table.
	 *
	 * @param teacherID ID of teacher
	 * @param newName New name of teacher
	 * @param newFamilyName New family name of teacher
	 *
	 * @throws SQLException
	 * @return Nothing.
	 * */
	public void update(int teacherID, String newName, String newFamilyName) throws SQLException;

	/**
	 * This method delete teacher with specific name and family name from teachers table.
	 *
	 * @param name Name of teacher
	 * @param familyName Family name of teacher
	 *
	 * @throws SQLException
	 * @return Nothing
	 * */
	public void delete(String name, String familyName) throws SQLException;


	/**
	 * Method delete teacher with specific name and family name as curator from group with specific name
	 *
	 * @param teacherID ID of teacher
	 * @param groupName Name of group
	 *
	 * @throws SQLException
	 * @return Nothing
	 * */
	public void deleteCurator(int teacherID, String groupName) throws SQLException;

	/**
	 * This method return list of all teachers who have a specific name.
	 * 
	 * @param name Name of teacher for whom there is a search
	 * @throws SQLException
	 * @return List of teachers who have a specific name
	 * */
	public List<Teacher> getByName(String name) throws SQLException;

	/**
	 * This method return list of all teachers who have a specific family name.
	 * 
	 * @param familyName Family name of teacher for whom there is a search
	 * @throws SQLException
	 * @return List of teachers who have a specific family name
	 * */
	public List<Teacher> getByFamilyName(String familyName) throws SQLException;

	/**
	 * Method return list of teachers who have specific name and specific family name
	 *
	 * @param name Name of teacher
	 * @param familyName Family name of teachers
	 * @throws SQLException
	 * @return List of teachers who have specific name and specific family name
	 * */
	public List<Teacher> getTeacher(String name, String familyName) throws SQLException;
	

	/**
	 * This method return list of all teachers.
	 *
	 * @throws SQLException
	 * @return List of teachers
	 * */
	public List<Teacher> getAll() throws SQLException;

	/**
	 * Method return list of teachers by group's name
	 *
	 * @param groupName Name of group
	 *
	 * @throws SQLException
	 * @return List List of teacher
	 * */
	public List<Teacher> getByGroup(String groupName) throws SQLException;
}
