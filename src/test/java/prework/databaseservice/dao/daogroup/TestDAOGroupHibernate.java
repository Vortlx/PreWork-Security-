package prework.databaseservice.dao.daogroup;


import org.junit.Test;

import java.sql.SQLException;
import java.util.HashSet;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;

import prework.databaseservice.dao.DAOGroup;
import prework.data.Group;
import prework.data.Student;
import prework.data.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDAOGroupHibernate {

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("spring/Spring.cfg.xml");
    private static final DAOGroup daoGroup = (DAOGroup) context.getBean("daoGroupHibernate");
    private static final String testGroupName = "TestGroup";
    
    @BeforeClass
    public static void addDataToTable(){
        try{
            daoGroup.add(testGroupName);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void deleteDataFromTable(){
        try{
            daoGroup.delete(testGroupName);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Ignore("I don't know how testing this method!")
    @Test
    public void testUpdate(){
        
    }
    
    @Test
    public void testGetByName(){
        Group group = new Group();
        group.setName(testGroupName);
        group.setStudents(new HashSet<Student>());
        group.setTeachers(new HashSet<Teacher>());
        
        try{
            Group groupFromTable = daoGroup.getByName(testGroupName);

            Assert.assertEquals(group, groupFromTable);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Ignore("I don't know how testing this method!")
    @Test
    public void testGetAll(){
        
    }
}
