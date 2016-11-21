package prework.dao.daostudent;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;

import prework.dao.DAOGroup;
import prework.dao.DAOStudent;
import prework.entities.Group;
import prework.entities.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Ignore
public class TestDAOStudentHibernate {

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("spring/Spring.cfg.xml");
    private static final DAOGroup daoGroup = (DAOGroup) context.getBean("daoGroupHibernate");
    private static final DAOStudent daoStudent = (DAOStudent) context.getBean("daoStudentHibernate");
    private static final String studentName = "TestName";
    private static final String studentFamilyName = "TestFamilyName";
    private static final String testGroupName1 = "TestGroupName1";
    private static final String testGroupName2 = "TestGroupName2";

    private static Student testStudent = null;
    private static Group testGroup1 = null;
    private static Group testGroup2 = null;
    
    @BeforeClass
    public static void addDataToTable(){
        try{
            //daoGroup.add(testGroupName1);
            //daoGroup.add(testGroupName2);

            testGroup1 = daoGroup.getByName(testGroupName1);
            testGroup2 = daoGroup.getByName(testGroupName2);

            //daoStudent.add(studentName, studentFamilyName, testGroup1.getId());
            testStudent = daoStudent.getStudent(studentName, studentFamilyName).iterator().next();


        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void deleteDataFromTable(){
        try{

            daoStudent.deleteById(testStudent.getId());

            daoGroup.deleteByID(testGroup1.getId());
            daoGroup.deleteByID(testGroup2.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}