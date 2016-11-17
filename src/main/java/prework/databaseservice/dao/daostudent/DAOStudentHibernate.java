package prework.databaseservice.dao.daostudent;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.data.UserInfo;
import prework.databaseservice.dao.DAOStudent;
import prework.data.Group;
import prework.data.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("daoStudentHibernate")
public class DAOStudentHibernate implements DAOStudent {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(String name, String familyName, int groupID, UserInfo userInfo) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        Group group = session.get(Group.class, groupID);

        Student student = new Student();
        student.setName(name);
        student.setFamilyName(familyName);
        student.setGroup(group);
        student.setUserInfo(userInfo);

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
        Group group = student.getGroup();

        return group;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Student getById(int studentId) {
        Session session = sessionFactory.getCurrentSession();

        Student student = session.get(Student.class, studentId);

        return student;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<Student> getAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Student";
        Query query = session.createQuery(queryString);
        Set<Student> students = new HashSet<Student>();
        students.addAll(query.getResultList());

        return students;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<Student> getByName(String name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Student where name = :name";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        Set<Student> students = new HashSet<Student>();
        students.addAll(query.getResultList());

        return students;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<Student> getByFamilyName(String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Student where familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("familyName", familyName);
        Set<Student> students = new HashSet<Student>();
        students.addAll(query.getResultList());

        return students;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<Student> getStudent(String name, String familyName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Student where name = :name and familyName = :familyName";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);
        Set<Student> students = new HashSet<Student>();
        students.addAll(query.getResultList());

        return students;
    }
}