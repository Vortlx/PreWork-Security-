package prework.databaseservice.dao.daostudent;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import prework.databaseservice.dao.DAOGroup;
import prework.databaseservice.dao.DAOStudent;
import prework.data.Group;
import prework.data.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestDAOStudentHibernate {

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("spring/Spring.cfg.xml");
    private static final DAOGroup daoGroup = (DAOGroup) context.getBean("daoGroupHibernate");
    private static final DAOStudent daoStudent = (DAOStudent) context.getBean("daoStudentHibernate");
    private static final String studentName = "TestName";
    private static final String studentFamilyName = "TestFamilyName";
    private static final String groupName = "TestGroupName";
    
    @BeforeClass
    public static void addDataToTable(){
        try{
            daoGroup.add(groupName);
            daoStudent.add(studentName, studentFamilyName, groupName);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void deleteDataFromTable(){
        try{
            daoGroup.delete(groupName);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Ignore("I don't know how testing this method!")
    @Test
    public void testUpdate(){
        
    }
    
    @Ignore("I don't know how testing this method!")
    @Test
    public void testUpdateGroup(){
        
    }
    
    @Ignore("I don't know how testing this method!")
    @Test
    public void testGetAll(){
        
    }
    
    @Test
    public void testGetByName(){
        try{
            Group group = daoGroup.getByName(groupName);
            Student student = new Student();
            student.setName(studentName);
            student.setFamilyName(studentFamilyName);
            student.setGroup(group);
            
            List<Student> students = daoStudent.getByName(studentName);

            Assert.assertTrue(students.contains(student));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetByFamilyName(){
        try{
            Group group = daoGroup.getByName(groupName);
            Student student = new Student();
            student.setName(studentName);
            student.setFamilyName(studentFamilyName);
            student.setGroup(group);
            
            List<Student> students = daoStudent.getByFamilyName(studentFamilyName);
            
            Assert.assertTrue(students.contains(student));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetStudent(){
        try{
            Group group = daoGroup.getByName(groupName);
            Student student = new Student();
            student.setName(studentName);
            student.setFamilyName(studentFamilyName);
            student.setGroup(group);

            List<Student> students = daoStudent.getStudent(studentName, studentFamilyName);
            
            Assert.assertTrue(students.contains(student));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}