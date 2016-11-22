package prework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import prework.dao.DAODepartment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import prework.dao.DAOGroup;
import prework.dao.DAOSubject;
import prework.entities.Group;
import prework.entities.Student;
import prework.entities.Teacher;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * This Class using for testing other classes.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 */
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring\\Spring.cfg.xml");

        DAOSubject daoSubject = (DAOSubject)(context.getBean("test"));

        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //HibernateUtil.closeSessionFactory();
        }
    }
}