package prework.databaseservice.dao.daogroup;


import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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


@Ignore
public class TestDAOGroupHibernate {

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("spring/Spring.cfg.xml");
    private static final DAOGroup daoGroup = (DAOGroup) context.getBean("daoGroupHibernate");
    private static final String testGroupName = "TestGroup";

    private static Group testGroup = null;
    
    @BeforeClass
    public static void addDataToTable(){
        try{
            daoGroup.add(testGroupName);
            testGroup = daoGroup.getByName(testGroupName);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void deleteDataFromTable(){
        try{
            daoGroup.deleteByName(testGroupName);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testChangeName(){

        try{
            String newGroupTestName = "newGroupName";
            daoGroup.changeName(testGroup.getId(), newGroupTestName);

            Group newTestGroup = daoGroup.getByID(testGroup.getId());
            Assert.assertFalse(newTestGroup.getName().equals(testGroupName));
            Assert.assertTrue(newTestGroup.getName().equals(newGroupTestName));

            daoGroup.changeName(testGroup.getId(), testGroupName);
            Assert.assertTrue(testGroup.getName().equals(testGroupName));
            Assert.assertFalse(testGroup.getName().equals(newGroupTestName));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}