package prework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import prework.dao.crudinterface.DAOSubject;

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