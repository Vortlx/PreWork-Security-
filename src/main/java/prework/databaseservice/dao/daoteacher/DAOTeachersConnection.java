package prework.databaseservice.dao.daoteacher;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import prework.databaseservice.dao.DAOTeacher;
import prework.data.Group;
import prework.data.Teacher;

import java.util.ArrayList;

import static prework.resources.Resources.getProperty;

/**
 * This class implements CRUD operation for teachers table.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Deprecated
public class DAOTeachersConnection implements DAOTeacher{

	static{
		try{
			Class.forName(getProperty("DRIVER_CLASS"));
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	/**
	 * This method add new teacher into teachers table.
	 *
	 * @see DAOTeacher#add(String, String, Group...)
	 *
	 * @param name Name of teacher
	 * @param familyName Family name of teacher
	 * @param groups Array of groups which under this teacher
	 *
	 *  @throws SQLException
	 *  @return Nothing.
	 * */
	public void add(String name, String familyName) throws SQLException {
		Connection conn = DriverManager.getConnection(getProperty("URL"));

		String query = "INSERT INTO teachers (name, family_name) VALUES(?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, familyName);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}

	/**
	 * Method make teacher with specific name and family name curator of group with specific name
	 *
	 * @see DAOTeacher#addGroup(String, String, String)
	 *
	 * @param teacherID ID of teacher
	 * @param groupName Name of group
	 *
	 * @throws SQLException
	 * @return Nothing
	 * */
	public void addGroup(int teacherID, String groupName) throws SQLException{
		Connection conn = DriverManager.getConnection(getProperty("URL"));

		int groupID = -1;

		String getGroupIDQuery = "SELECT id FROM groups " +
									"WHERE name = '" + groupName + "'";
		Statement getGroupIDStat = conn.createStatement();
		ResultSet groupRS = getGroupIDStat.executeQuery(getGroupIDQuery);
		if(groupRS.next()){
			groupID = groupRS.getInt(1);
		}else{
			groupRS.close();
			getGroupIDStat.close();
			conn.close();

			throw (new SQLException());
		}
		groupRS.close();
		getGroupIDStat.close();

		String query = "INSERT INTO curator(id_group, id_teacher) VALUES(?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, groupID);
		statement.setInt(2, teacherID);
		statement.executeUpdate();

		statement.close();
		conn.close();
	}

	/**
	 * This method update data into teachers table.
	 *
	 * @see DAOTeacher#update(String, String, String, String)
	 *
	 * @param teacherID ID of teacher
	 * @param newName New name of teacher
	 * @param newFamilyName New family name of teacher
	 *
	 * @throws SQLException
	 * @return Nothing.
	 * */
	public void update(int teacherID, String newName, String newFamilyName) throws SQLException {
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "UPDATE teachers SET name = ?, family_name = ? WHERE id = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(3, teacherID);
		statement.setString(1, newName);
		statement.setString(2, newFamilyName);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}

	/**
	 * This method delete teacher with specific name and family name from teachers table.
	 *
	 * @see DAOTeacher#delete(String, String)
	 *
	 * @param name Name of teacher
	 * @param familyName Family name of teacher
	 *
	 * @throws SQLException
	 * @return Nothing
	 * */
	public void delete(String name, String familyName) throws SQLException {
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "DELETE FROM teachers WHERE name = ? AND family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, familyName);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}

	/**
	 * Method delete teacher with specific name and family name as curator from group with specific name
	 *
	 * @see DAOTeacher#deleteCurator(String, String, String)
	 *
	 * @param teacherID ID of teacher
	 * @param groupName Name of group
	 *
	 * @throws SQLException
	 * @return Nothing
	 * */
	public void deleteCurator(int teacherID, String groupName) throws SQLException{
		Connection conn = DriverManager.getConnection(getProperty("URL"));

		int groupID = -1;

		String getGroupIDQuery = "SELECT id FROM groups " +
				"WHERE name = '" + groupName + "'";
		Statement getGroupIDStat = conn.createStatement();
		ResultSet groupRS = getGroupIDStat.executeQuery(getGroupIDQuery);
		if(groupRS.next()){
			groupID = groupRS.getInt(1);
		}else{
			groupRS.close();
			getGroupIDStat.close();
			conn.close();

			throw (new SQLException());
		}
		groupRS.close();
		getGroupIDStat.close();

		String query = "DELETE FROM curator " +
						"WHERE id_group = ? AND id_teacher = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, groupID);
		statement.setInt(2, teacherID);
		statement.executeUpdate();

		statement.close();
		conn.close();
	}

	/**
	 * This method return list of all teachers who have a specific name.
	 *
	 * @see DAOTeacher#getByName(String)
	 *
	 * @param name Name of teacher for whom there is a search
	 * @throws SQLException
	 * @return List of teachers who have a specific name
	 * */
	public List<Teacher> getByName(String name) throws SQLException {
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		List<Teacher> res = new ArrayList<Teacher>();
		
		String query = "SELECT teachers.id, teachers.name, teachers.family_name "
						+ "FROM teachers WHERE name = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			int teacherID = rs.getInt(1);
			String familyName = rs.getString(3);
			
			Teacher teacher = new Teacher(teacherID, name, familyName, familyName + name, "test");
			
			String getGroupQuery = "SELECT groups.name, groups.id "
									+ "FROM groups INNER JOIN curator "
									+ "WHERE groups.id = curator.id_group "
									+ "AND curator.id_teacher = ?";
			
			PreparedStatement getGroupStat = conn.prepareStatement(getGroupQuery);
			getGroupStat.setInt(1, teacherID);
			ResultSet groupRS = getGroupStat.executeQuery();
			while(groupRS.next()){
				String groupName = groupRS.getString(1);
				int groupID = groupRS.getInt(2);
				
				Group group = new Group(groupID, groupName);
			}
			
			res.add(teacher);
			
			groupRS.close();
			getGroupStat.close();
		}
		
		rs.close();
		statement.close();
		conn.close();
		
		return res;
	}

	/**
	 * This method return list of all teachers who have a specific family name.
	 *
	 * @see DAOTeacher#getByName(String)
	 *
	 * @param familyName Family name of teacher for whom there is a search
	 * @throws SQLException
	 * @return List of teachers who have a specific family name
	 * */
	public List<Teacher> getByFamilyName(String familyName) throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		List<Teacher> res = new ArrayList<Teacher>();
		
		String query = "SELECT teachers.id, teachers.name, teachers.family_name "
						+ "FROM teachers WHERE family_name = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, familyName);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			int teacherID = rs.getInt(1);
			String name = rs.getString(2);
			
			Teacher teacher = new Teacher(teacherID, name, familyName, familyName + name, "tets");
			
			String getGroupQuery = "SELECT groups.name, groups.id "
									+ "FROM groups INNER JOIN curator "
									+ "WHERE groups.id = curator.id_group "
									+ "AND curator.id_teacher = ?";
			
			PreparedStatement getGroupStat = conn.prepareStatement(getGroupQuery);
			getGroupStat.setInt(1, teacherID);
			ResultSet groupRS = getGroupStat.executeQuery();
			while(groupRS.next()){
				String groupName = groupRS.getString(1);
				int groupID = groupRS.getInt(2);
				
				Group group = new Group(groupID, groupName);
			}
			
			res.add(teacher);
			
			groupRS.close();
			getGroupStat.close();
		}
		
		rs.close();
		statement.close();
		conn.close();
		
		return res;
	}

	/**
	 * Method return list of teachers who have specific name and specific family name
	 *
	 * @see DAOTeacher#getTeacher(String, String)
	 *
	 * @param name Name of teacher
	 * @param familyName Family name of teachers
	 * @throws SQLException
	 * @return List of teachers who have specific name and specific family name
	 * */
	public List<Teacher> getTeacher(String name, String familyName) throws SQLException{

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		List<Teacher> res = new ArrayList<Teacher>();
		
		String query = "SELECT teachers.id, teachers.name, teachers.family_name "
						+ "FROM teachers WHERE name = ? AND family_name = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, familyName);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			int teacherID = rs.getInt(1);
			
			Teacher teacher = new Teacher(teacherID, name, familyName, familyName + name, "tets");
			
			String getGroupQuery = "SELECT groups.name, groups.id "
									+ "FROM groups INNER JOIN curator "
									+ "WHERE groups.id = curator.id_group "
									+ "AND curator.id_teacher = ?";
			
			PreparedStatement getGroupStat = conn.prepareStatement(getGroupQuery);
			getGroupStat.setInt(1, teacherID);
			ResultSet groupRS = getGroupStat.executeQuery();
			while(groupRS.next()){
				String groupName = groupRS.getString(1);
				int groupID = groupRS.getInt(2);
				
				Group group = new Group(groupID, groupName);
			}
			
			res.add(teacher);
			
			groupRS.close();
			getGroupStat.close();
		}
		
		rs.close();
		statement.close();
		conn.close();
		
		return res;
	}


	/**
	 * This method return list of all teachers.
	 *
	 * @see DAOTeacher#getAll()
	 *
	 * @throws SQLException
	 * @return List of teachers
	 * */
	public List<Teacher> getAll() throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		List<Teacher> res = new ArrayList<Teacher>();
		
		String query = "SELECT teachers.id, teachers.name, teachers.family_name "
						+ "FROM teachers";
		
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			int teacherID = rs.getInt(1);
			String name = rs.getString(2);
			String familyName = rs.getString(3);
			Teacher teacher = new Teacher(teacherID, name, familyName, familyName + name, "tets");
			
			String getGroupQuery = "SELECT groups.name, groups.id "
									+ "FROM groups INNER JOIN curator "
									+ "WHERE groups.id = curator.id_group "
									+ "AND curator.id_teacher = ?";
			
			PreparedStatement getGroupStat = conn.prepareStatement(getGroupQuery);
			getGroupStat.setInt(1, teacherID);
			ResultSet groupRS = getGroupStat.executeQuery();
			while(groupRS.next()){
				String groupName = groupRS.getString(1);
				int groupID = groupRS.getInt(2);
				
				Group group = new Group(groupID, groupName);
			}
			
			res.add(teacher);
			
			groupRS.close();
			getGroupStat.close();
		}
		
		rs.close();
		statement.close();
		conn.close();
		
		return res;
	}

	/**
	 * Method return list of teachers by group's name
	 *
	 * @see DAOTeacher#getByGroup(String)
	 *
	 * @param groupName Name of group
	 *
	 * @throws SQLException
	 * @return List List of teacher
	 * */
	public List<Teacher> getByGroup(String groupName) throws SQLException{
		Connection conn = DriverManager.getConnection(getProperty("URL"));

		List<Teacher> res = new ArrayList<Teacher>();

		String query = "SELECT teachers.id, teachers.name, teachers.family_name " +
						"FROM teachers INNER JOIN ( " +
						"SELECT curator.id_teacher FROM groups INNER JOIN curator " +
						"WHERE groups.id = curator.id_group AND groups.name = ?) AS tmp " +
						"WHERE teachers.id = tmp.id_teacher";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, groupName);
		ResultSet rs = statement.executeQuery();

		while(rs.next()){
			int teacherID = rs.getInt(1);
			String teacherName = rs.getString(2);
			String teacherFamilyName = rs.getString(3);

			res.add(new Teacher(teacherID, teacherName, teacherFamilyName, teacherFamilyName + teacherName, "tets"));
		}

		rs.close();
		statement.close();
		conn.close();

		return res;
	}
}