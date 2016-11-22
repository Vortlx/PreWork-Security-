package prework.dao.impl;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.*;
import prework.dao.DAOTeacher;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public class DAOTeacherImpl implements DAOTeacher {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(Teacher teacher) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.save(teacher);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeFullName(int teacherId, String newName, String newFamilyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "update Teacher set name = :newName, familyName = :newFamilyName where id = :id";
        Query query = session.createQuery(queryString);
        query.setParameter("id", teacherId);
        query.setParameter("newName", newName);
        query.setParameter("newFamilyName", newFamilyName);
        query.executeUpdate();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteById(int teacherId) {
        Session session = sessionFactory.getCurrentSession();

        Teacher teacher = session.get(Teacher.class, teacherId);

        session.delete(teacher);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByFullName(String name, String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "delete Teacher where name = :name and familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);

        query.executeUpdate();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Subject getSubject(int teacherId) {
        Session session = sessionFactory.getCurrentSession();

        Teacher teacher = session.get(Teacher.class, teacherId);

        return teacher.getSubject();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Teacher getById(int teacherId) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Teacher.class, teacherId);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Teacher> getByName(String name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Teacher where name = :name";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Teacher> getByFamilyName(String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Teacher where familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("familyName", familyName);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Teacher> getTeacher(String name, String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Teacher where name = :name and familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Teacher> getAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Teacher";
        Query query = session.createQuery(queryString);

        return query.getResultList();
    }
}