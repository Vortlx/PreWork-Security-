package prework.dao.impl;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.User;
import prework.dao.DAOStudent;
import prework.entities.Group;
import prework.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public class DAOStudentImpl implements DAOStudent {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(Student student) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        session.save(student);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeFullName(int studentId, String newName, String newFamilyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        Student student = session.get(Student.class, studentId);
        student.setName(newName);
        student.setFamilyName(newFamilyName);
        session.update(student);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeGroup(int studentId, int newGroupId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        Group newGroup = session.get(Group.class, newGroupId);
        Student student = session.get(Student.class, studentId);

        student.setGroup(newGroup);
        session.update(student);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteById(int studentId) {
        Session session = sessionFactory.getCurrentSession();

        Student student = session.get(Student.class, studentId);
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
    public Group getGroup(int studentId) {
        Session session = sessionFactory.getCurrentSession();

        Student student = session.get(Student.class, studentId);

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