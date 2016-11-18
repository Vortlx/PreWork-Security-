package prework.databaseservice.dao.daoteacher;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.*;
import prework.databaseservice.dao.DAOTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("daoTeacherHibernate")
public class DAOTeacherHibernate implements DAOTeacher {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(String name, String familyName,
                    Subject subject, Department department, UserInfo userInfo) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setFamilyName(familyName);
        teacher.setSubject(subject);
        teacher.setDepartment(department);
        teacher.setUserInfo(userInfo);

        session.save(teacher);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeFullName(int teacherID, String newName, String newFamilyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "update Teacher set name = :newName, familyName = :newFamilyName where id = :id";
        Query query = session.createQuery(queryString);
        query.setParameter("id", teacherID);
        query.setParameter("newName", newName);
        query.setParameter("newFamilyName", newFamilyName);
        query.executeUpdate();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByID(int teacherID) {
        Session session = sessionFactory.getCurrentSession();

        Teacher teacher = session.get(Teacher.class, teacherID);

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
    public Subject getSubject(int teacherID) {
        Session session = sessionFactory.getCurrentSession();

        Teacher teacher = session.get(Teacher.class, teacherID);
        Subject subject = teacher.getSubject();

        return subject;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Teacher getById(int teacherID) {
        Session session = sessionFactory.getCurrentSession();

        Teacher teacher = session.get(Teacher.class, teacherID);

        return teacher;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<Teacher> getByName(String name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Teacher where name = :name";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        Set<Teacher> teachers = new HashSet<Teacher>();
        teachers.addAll(query.getResultList());

        return teachers;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<Teacher> getByFamilyName(String familyName) throws SQLException {

        Session session = sessionFactory.getCurrentSession();


        String queryString = "from Teacher where familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("familyName", familyName);
        Set<Teacher> teachers = new HashSet<Teacher>();
        teachers.addAll(query.getResultList());

        return teachers;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<Teacher> getTeacher(String name, String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Teacher where name = :name and familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);
        Set<Teacher> teachers = new HashSet<Teacher>();
        teachers.addAll(query.getResultList());

        return teachers;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<Teacher> getAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Teacher";
        Query query = session.createQuery(queryString);
        Set<Teacher> teachers = new HashSet<Teacher>();
        teachers.addAll(query.getResultList());

        return teachers;
    }
}