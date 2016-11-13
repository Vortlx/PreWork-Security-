package prework;

import java.sql.SQLException;
import java.util.List;

import prework.data.Group;
import prework.data.Student;
import prework.data.Subject;
import prework.databaseservice.dao.DAOTeacher;

import prework.databaseservice.dao.DAOGroup;
import prework.databaseservice.dao.DAOStudent;
import prework.data.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This Class using for testing other classes.
 * 
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class Main{
	public static void main(String[] args){

		ApplicationContext context = new ClassPathXmlApplicationContext("spring\\Spring.cfg.xml");

	    DAOTeacher daoTeacher = (DAOTeacher)context.getBean("daoTeacherHibernate");
		DAOGroup daoGroup = (DAOGroup)context.getBean("daoGroupHibernate");
		DAOStudent daoStudent = (DAOStudent)context.getBean("daoStudentHibernate");

	    try{

			String groupName = "113";
			Group group = daoGroup.getByName(groupName);
/*
			for(Subject subject: group.getSubjects()){
				System.out.println(subject.getName() + ":	" + subject.getType());

				for(Teacher teacher: subject.getTeachers()){
					System.out.println("	" + teacher.getName() + " " + teacher.getFamilyName() + " " + teacher.getRole().getName());
				}
				System.out.println();
			}
*/	        
			System.out.println(group.getDepartment().getName() + " " + group.getDepartment().getRole().getName());
			System.out.println();
			System.out.println();
			for(Student student: group.getStudents()){
			    System.out.println(student.getName() + " " + student.getFamilyName() + " " + student.getRole().getName());
			}
	    }catch(SQLException e){
	        e.printStackTrace();
	    }finally{
	    	//HibernateUtil.closeSessionFactory();
		}
	}
}