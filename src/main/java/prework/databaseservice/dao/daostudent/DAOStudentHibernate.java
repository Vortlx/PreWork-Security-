package prework.databaseservice.dao.daostudent;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import prework.databaseservice.dao.DAOStudent;
import prework.data.Group;
import prework.data.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("daoStudentHibernate")
public class DAOStudentHibernate implements DAOStudent{

    @Autowired
    private SessionFactory sessionFactory;

    public void add(String name, String familyName, String groupName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String getGroupQuery = "from Group where name = :name";
        Query query = session.createQuery(getGroupQuery);
        query.setParameter("name", groupName);
        Group group = (Group) query.getSingleResult();
        
        Student student = new Student();
        student.setName(name);
        student.setFamilyName(familyName);
        student.setGroup(group);
        
        session.save(student);
        
        session.getTransaction().commit();
    }

    public void update(int studentID, String newName, String newFamilyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();

        String queryString = "update Student set name = :newName, familyName = :newFamilyName where id = :id";
        Query query = session.createQuery(queryString);
        query.setParameter("id", studentID);
        query.setParameter("newName", newName);
        query.setParameter("newFamilyName", newFamilyName);
        query.executeUpdate();
        
        session.getTransaction().commit();
    }

    public void updateGroup(int studentID, String newGroupName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();

        String getGroupQuery = "from Group where name = :groupName";
        Query query = session.createQuery(getGroupQuery);
        query.setParameter("groupName", newGroupName);
        Group newGroup = (Group) query.getSingleResult();
        
        Student student = session.get(Student.class, studentID);

        if(!student.getGroup().equals(newGroup)){
            student.setGroup(newGroup);
            session.update(student);
        }

        session.getTransaction().commit();
    }

    public void delete(String name, String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();

        String queryString = "delete Student where name = :name and familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);
        query.executeUpdate();
        
        session.getTransaction().commit();
    }

    public List<Student> getAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String queryString = "from Student";
        Query query = session.createQuery(queryString);
        List<Student> students = query.getResultList();
        
        session.getTransaction().commit();
        
        return students;
    }

    public List<Student> getByName(String name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String queryString = "from Student where name = :name";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        List<Student> students = query.getResultList();
        
        session.getTransaction().commit();
        
        return students;
    }

    public List<Student> getByFamilyName(String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String queryString = "from Student where familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("familyName", familyName);
        List<Student> students = query.getResultList();
        
        session.getTransaction().commit();
        
        return students;
    }

    public List<Student> getStudent(String name, String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String queryString = "from Student where name = :name and familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);
        List<Student> students = query.getResultList();
        
        session.getTransaction().commit();
        
        return students;
    }
}
