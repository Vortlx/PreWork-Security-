package prework.databaseservice.dao.daoteacher;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import prework.databaseservice.dao.DAOTeacher;
import prework.data.Group;
import prework.data.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestDAOTeacherHibernate {

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("spring/Spring.cfg.xml");
    private static final DAOTeacher daoTeacher = (DAOTeacher) context.getBean("daoTeacherHibernate");
    private static final String teacherName = "TestName";
    private static final String teacherFamilyName = "TestFamilyName";
    
    @BeforeClass
    public static void addDataToTable(){
        try{
            daoTeacher.add(teacherName, teacherFamilyName);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void deleteDataFromTable(){
        try{
            daoTeacher.delete(teacherName, teacherFamilyName);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Ignore("I don't know how testing this method!")
    @Test
    public void testAddGroup(){
        
    }
    
    @Ignore("I don't know how testing this method!")
    @Test
    public void testUpdate(){
        
    }
    
    @Ignore("I don't know how testing this method!")
    @Test
    public void testDeleteCurator(){
        
    }
    
    @Test
    public void testGetByName(){
        try{
            Teacher teacher = new Teacher();
            teacher.setName(teacherName);
            teacher.setFamilyName(teacherFamilyName);
            teacher.setGroup(new HashSet<Group>());
            
            List<Teacher> teachers = daoTeacher.getByName(teacherName);
            
            Assert.assertTrue(teachers.contains(teacher));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetByFamilyName(){
        try{
            Teacher teacher = new Teacher();
            teacher.setName(teacherName);
            teacher.setFamilyName(teacherFamilyName);
            teacher.setGroup(new HashSet<Group>());
            
            List<Teacher> teachers = daoTeacher.getByFamilyName(teacherFamilyName);
            
            Assert.assertTrue(teachers.contains(teacher));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetTeacher(){
        try{
            Teacher teacher = new Teacher();
            teacher.setName(teacherName);
            teacher.setFamilyName(teacherFamilyName);
            teacher.setGroup(new HashSet<Group>());
            
            List<Teacher> teachers = daoTeacher.getTeacher(teacherName, teacherFamilyName);
            
            Assert.assertTrue(teachers.contains(teacher));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Ignore("I don't know how testing this method!")
    @Test
    public void testGetAll(){
        
    }
    
    @Ignore("Coming soon")
    @Test
    public void testGetByGroup(){
        
    }
}