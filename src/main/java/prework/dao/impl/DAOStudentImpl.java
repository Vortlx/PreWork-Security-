package prework.dao.impl;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.dao.DAOStudent;
import prework.entities.Group;
import prework.entities.Student;


@Repository
public class DAOStudentImpl implements DAOStudent {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(Student student) throws SQLException {
        entityManager.persist(student);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeFullName(int studentId, String newName, String newFamilyName) throws SQLException {
        Student student = entityManager.find(Student.class, studentId);
        student.setName(newName);
        student.setFamilyName(newFamilyName);
        entityManager.merge(student);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeGroup(int studentId, int newGroupId) throws SQLException {
        Group newGroup = entityManager.find(Group.class, newGroupId);
        Student student = entityManager.find(Student.class, studentId);

        student.setGroup(newGroup);
        entityManager.merge(student);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteById(int studentId) {
        Student student = entityManager.find(Student.class, studentId);
        entityManager.remove(student);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByFullName(String name, String familyName) throws SQLException {
        String queryString = "delete Student where name = :name and familyName = :familyName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);

        query.executeUpdate();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getGroup(int studentId) {
        Student student = entityManager.find(Student.class, studentId);

        return student.getGroup();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Student getById(int studentId) {
        Student student = entityManager.find(Student.class, studentId);

        return student;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getAll() throws SQLException {
        String queryString = "from Student";
        Query query = entityManager.createQuery(queryString);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getByName(String name) throws SQLException {
        String queryString = "from Student where name = :name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getByFamilyName(String familyName) throws SQLException {
        String queryString = "from Student where familyName = :familyName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("familyName", familyName);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getStudent(String name, String familyName) throws SQLException {
        String queryString = "from Student where name = :name and familyName = :familyName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);

        return query.getResultList();
    }
}