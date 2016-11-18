package prework.dao.impl;


import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.Department;
import prework.entities.Student;
import prework.entities.Subject;
import prework.dao.DAOGroup;
import prework.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("daoGroupHibernate")
public class DAOGroupHibernate implements DAOGroup {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(String name, Department department) {

        Session session = sessionFactory.getCurrentSession();

        Group group = new Group();
        group.setName(name);
        group.setDepartment(department);
        session.save(group);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addStudent(int groupID, Student student) {
        Session session = sessionFactory.getCurrentSession();

        Group group = session.get(Group.class, groupID);
        group.addStudent(student);
        session.update(group);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addSubject(int groupID, Subject subject) {
        Session session = sessionFactory.getCurrentSession();

        Group group = session.get(Group.class, groupID);
        group.addSubject(subject);
        subject.addGroup(group);

        session.update(group);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByID(int groupID) {
        Session session = sessionFactory.getCurrentSession();

        Group group = session.get(Group.class, groupID);
        session.delete(group);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByName(String groupName) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupByNameQuery = "from Group where name = :name";
        Query query = session.createQuery(getGroupByNameQuery);
        query.setParameter("name", groupName);
        Group group = (Group) query.getSingleResult();
        session.delete(group);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteSubject(int groupId, int subjectId) {
        Session session = sessionFactory.getCurrentSession();

        Subject subject = session.get(Subject.class, subjectId);
        Group group = session.get(Group.class, groupId);

        subject.deleteGroup(group);
        group.deleteSubject(subject);

        session.update(group);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getByID(int groupID) {
        Session session = sessionFactory.getCurrentSession();

        Group group = session.get(Group.class, groupID);

        return group;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Group where name=:name";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        Group group = (Group) query.getSingleResult();

        return group;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getAll() {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from Group";
        Query query = session.createQuery(queryString);
        List<Group> groups = query.getResultList();

        return groups;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getStudents(int groupID) {
        Session session = sessionFactory.getCurrentSession();

        String getStudentsQuery = "select students from Group where id = :id";
        Query query = session.createQuery(getStudentsQuery);
        query.setParameter("id", groupID);
        List<Student> students = (List<Student>) query.getSingleResult();

        return students;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Student getStudent(int groupID, String studentName, String studentFamilyName) {
        Session session = sessionFactory.getCurrentSession();

        String getStudentsQuery = "select students from Group where id = :id";
        Query query = session.createQuery(getStudentsQuery);
        query.setParameter("id", groupID);
        List<Student> students = (List<Student>) query.getSingleResult();

        for (Student student : students) {
            if (student.getName().equals(studentName) &&
                    student.getName().equals(studentFamilyName)) {
                return student;
            }
        }

        return new Student();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Subject> getSubjects(int groupID) {
        Session session = sessionFactory.getCurrentSession();

        String getSubjectsQuery = "select subjects from Group where id = :id";
        Query query = session.createQuery(getSubjectsQuery);
        query.setParameter("id", groupID);
        List<Subject> subjects = (List<Subject>) query.getSingleResult();

        return subjects;
    }
}