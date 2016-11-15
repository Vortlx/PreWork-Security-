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

@Ignore
public class TestDAOTeacherHibernate {

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("spring/Spring.cfg.xml");
    private static final DAOTeacher daoTeacher = (DAOTeacher) context.getBean("daoTeacherHibernate");
    private static final String teacherName = "TestName";
    private static final String teacherFamilyName = "TestFamilyName";
    
    @BeforeClass
    public static void addDataToTable(){
        try{
            //daoTeacher.add(teacherName, teacherFamilyName);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void deleteDataFromTable(){
        try{
            daoTeacher.deleteByFullName(teacherName, teacherFamilyName);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}