package prework.databaseservice.dao.daogroup;


import org.junit.Test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;

import prework.databaseservice.dao.DAOGroup;
import prework.entities.Group;
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
            //daoGroup.add(testGroupName,);
            testGroup = daoGroup.getByName(testGroupName);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void deleteDataFromTable(){
        try{
            daoGroup.deleteByName(testGroupName);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testChangeName(){

    }
}