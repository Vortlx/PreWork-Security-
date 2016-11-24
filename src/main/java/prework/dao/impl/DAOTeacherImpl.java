package prework.dao.impl;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.*;
import prework.dao.DAOTeacher;


@Repository
public class DAOTeacherImpl implements DAOTeacher {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(Teacher teacher) throws SQLException {
        entityManager.persist(teacher);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeFullName(int teacherId, String newName, String newFamilyName) throws SQLException {
        String queryString = "update Teacher set name = :newName, familyName = :newFamilyName where id = :id";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", teacherId);
        query.setParameter("newName", newName);
        query.setParameter("newFamilyName", newFamilyName);
        query.executeUpdate();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteById(int teacherId) {
        Teacher teacher = entityManager.find(Teacher.class, teacherId);

        entityManager.remove(teacher);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByFullName(String name, String familyName) throws SQLException {
        String queryString = "delete Teacher where name = :name and familyName = :familyName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);

        query.executeUpdate();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Subject getSubject(int teacherId) {
        Teacher teacher = entityManager.find(Teacher.class, teacherId);

        return teacher.getSubject();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Teacher getById(int teacherId) {
        return entityManager.find(Teacher.class, teacherId);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Teacher> getByName(String name) throws SQLException {
        String queryString = "from Teacher where name = :name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Teacher> getByFamilyName(String familyName) throws SQLException {
        String queryString = "from Teacher where familyName = :familyName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("familyName", familyName);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Teacher> getTeacher(String name, String familyName) throws SQLException {
        String queryString = "from Teacher where name = :name and familyName = :familyName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Teacher> getAll() throws SQLException {
        String queryString = "from Teacher";
        Query query = entityManager.createQuery(queryString);

        return query.getResultList();
    }
}