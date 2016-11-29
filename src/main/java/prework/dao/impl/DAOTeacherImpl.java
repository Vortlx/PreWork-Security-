package prework.dao.impl;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import prework.entities.*;
import prework.dao.crudinterface.DAOTeacher;
import prework.dao.DAOTeacherCustom;


@Repository
public class DAOTeacherImpl implements DAOTeacherCustom {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private DAOTeacher daoteacher;

    public void changeFullName(int teacherId, String newName, String newFamilyName) throws SQLException {
        String queryString = "update Teacher set name = :newName, familyName = :newFamilyName where id = :id";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", teacherId);
        query.setParameter("newName", newName);
        query.setParameter("newFamilyName", newFamilyName);
        query.executeUpdate();
    }

    public void deleteByFullName(String name, String familyName) throws SQLException {
        String queryString = "delete Teacher where name = :name and familyName = :familyName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);

        query.executeUpdate();
    }

    public Subject getSubject(int teacherId) {
        Teacher teacher = entityManager.find(Teacher.class, teacherId);

        return teacher.getSubject();
    }

    public List<Teacher> getByName(String name) throws SQLException {
        String queryString = "from Teacher where name = :name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);

        return query.getResultList();
    }

    public List<Teacher> getByFamilyName(String familyName) throws SQLException {
        String queryString = "from Teacher where familyName = :familyName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("familyName", familyName);

        return query.getResultList();
    }

    public List<Teacher> getByNameAndFamilyName(String name, String familyName) throws SQLException {
        String queryString = "from Teacher where name = :name and familyName = :familyName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);

        return query.getResultList();
    }
}