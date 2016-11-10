package prework.databaseservice.dao.daoteacher;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import prework.data.Subject;
import prework.databaseservice.dao.DAOTeacher;
import prework.data.Group;
import prework.data.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("daoTeacherHibernate")
public class DAOTeacherHibernate implements DAOTeacher{

    @Autowired
    private SessionFactory sessionFactory;

    public void add(String name, String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setFamilyName(familyName);
        session.save(teacher);
        
        session.getTransaction().commit();
    }

    public void changeFullName(int teacherID, String newName, String newFamilyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String queryString = "update Teacher set name = :newName, familyName = :newFamilyName where id = :id";
        Query query = session.createQuery(queryString);
        query.setParameter("id", teacherID);
        query.setParameter("newName", newName);
        query.setParameter("newFamilyName", newFamilyName);
        query.executeUpdate();
        
        session.getTransaction().commit();
    }

    public void changeLogin(int teacherID, String newLogin) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Teacher teacher = session.get(Teacher.class, teacherID);
        teacher.setLogin(newLogin);
        session.update(teacher);

        session.getTransaction().commit();
    }

    public void changePassword(int teacherID, String newPassword) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Teacher teacher = session.get(Teacher.class, teacherID);
        teacher.setPassword(newPassword);
        session.update(teacher);

        session.getTransaction().commit();
    }

    public void delete(String name, String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String queryString = "delete Teacher where name = :name and familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);
        query.executeUpdate();
        
        session.getTransaction().commit();
    }

    public Subject getSubject(int teacherID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Teacher teacher = session.get(Teacher.class, teacherID);
        Subject subject = teacher.getSubject();

        session.getTransaction().commit();

        return subject;
    }

    public List<Teacher> getByName(String name) throws SQLException {
        
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String queryString = "from Teacher where name = :name";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        List<Teacher> teachers = query.getResultList();
        
        session.getTransaction().commit();
        
        return teachers;
    }

    public List<Teacher> getByFamilyName(String familyName) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String queryString = "from Teacher where familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("familyName", familyName);
        List<Teacher> teachers = query.getResultList();
        
        session.getTransaction().commit();
        
        return teachers;
    }

    public List<Teacher> getTeacher(String name, String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String queryString = "from Teacher where name = :name and familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);
        List<Teacher> teachers = query.getResultList();
        
        session.getTransaction().commit();
        
        return teachers;
    }

    public List<Teacher> getAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String queryString = "from Teacher";
        Query query = session.createQuery(queryString);
        List<Teacher> teachers = query.getResultList();
        
        session.getTransaction().commit();
        
        return teachers;
    }
}