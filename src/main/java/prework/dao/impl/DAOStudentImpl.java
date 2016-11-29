package prework.dao.impl;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import prework.dao.crudinterface.DAOStudent;
import prework.dao.DAOStudentCustom;
import prework.entities.Group;
import prework.entities.Student;


@Repository
public class DAOStudentImpl implements DAOStudentCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DAOStudent daoStudent;

    public void changeFullName(int studentId, String newName, String newFamilyName) throws SQLException {
        Student student = entityManager.find(Student.class, studentId);
        student.setName(newName);
        student.setFamilyName(newFamilyName);
        entityManager.merge(student);
    }

    public void changeGroup(int studentId, int newGroupId) throws SQLException {
        Group newGroup = entityManager.find(Group.class, newGroupId);
        Student student = entityManager.find(Student.class, studentId);

        student.setGroup(newGroup);
        entityManager.merge(student);
    }

    public void deleteByFullName(String name, String familyName) throws SQLException {
        String queryString = "delete Student where name = :name and familyName = :familyName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);

        query.executeUpdate();
    }

    public Group getGroup(int studentId) {
        Student student = entityManager.find(Student.class, studentId);

        return student.getGroup();
    }

    public List<Student> getByName(String name) throws SQLException {
        String queryString = "from Student where name = :name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);

        return query.getResultList();
    }

    public List<Student> getByFamilyName(String familyName) throws SQLException {
        String queryString = "from Student where familyName = :familyName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("familyName", familyName);

        return query.getResultList();
    }

    public List<Student> getByNameAndFamilyName(String name, String familyName) throws SQLException {
        String queryString = "from Student where name = :name and familyName = :familyName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);

        return query.getResultList();
    }
}