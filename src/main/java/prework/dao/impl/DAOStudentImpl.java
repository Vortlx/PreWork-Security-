package prework.dao.impl;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.User;
import prework.dao.DAOStudent;
import prework.entities.Group;
import prework.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("daoStudentHibernate")
public class DAOStudentImpl implements DAOStudent {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(String name, String familyName, int groupID, User user) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        Group group = session.get(Group.class, groupID);

        Student student = new Student();
        student.setName(name);
        student.setFamilyName(familyName);
        student.setGroup(group);
        student.setUser(user);

        session.save(student);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeFullName(int studentID, String newName, String newFamilyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        Student student = session.get(Student.class, studentID);
        student.setName(newName);
        student.setFamilyName(newFamilyName);
        session.update(student);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeGroup(int studentID, int newGroupId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        Group newGroup = session.get(Group.class, newGroupId);
        Student student = session.get(Student.class, studentID);

        if (!student.getGroup().equals(newGroup)) {
            student.setGroup(newGroup);
            session.update(student);
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByID(int studentID) {
        Session session = sessionFactory.getCurrentSession();

        Student student = session.get(Student.class, studentID);
        session.delete(student);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByFullName(String name, String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "delete Student where name = :name and familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);

        query.executeUpdate();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getGroup(int studentID) {
        Session session = sessionFactory.getCurrentSession();

        Student student = session.get(Student.class, studentID);

        return student.getGroup();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Student getById(int studentId) {
        Session session = sessionFactory.getCurrentSession();

        Student student = session.get(Student.class, studentId);

        return student;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Student";
        Query query = session.createQuery(queryString);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getByName(String name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Student where name = :name";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getByFamilyName(String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Student where familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("familyName", familyName);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getStudent(String name, String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Student where name = :name and familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);

        return query.getResultList();
    }
}