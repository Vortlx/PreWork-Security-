package prework.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.Group;
import prework.entities.Subject;
import prework.entities.SubjectType;
import prework.entities.Teacher;
import prework.dao.DAOSubject;

import javax.persistence.Query;
import java.util.List;

@Repository
public class DAOSubjectImpl implements DAOSubject {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(Subject subject) {
        Session session = sessionFactory.getCurrentSession();

        session.save(subject);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addGroup(int subjectId, Group group) {
        Session session = sessionFactory.getCurrentSession();

        Subject subject = session.get(Subject.class, subjectId);
        subject.addGroup(group);
        group.addSubject(subject);

        session.update(subject);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeName(int subjectId, String newName) {
        Session session = sessionFactory.getCurrentSession();

        Subject subject = session.get(Subject.class, subjectId);
        subject.setName(newName);
        session.update(subject);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteById(int subjectId) {
        Session session = sessionFactory.getCurrentSession();

        Subject subject = session.get(Subject.class, subjectId);
        session.delete(subject);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Subject getById(int subjectId) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Subject.class, subjectId);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Subject getByNameAndType(String name, SubjectType subjectType) {
        Session session = sessionFactory.getCurrentSession();

        String getSubjectQuery = "from Subject where name = :name and type = :subjectType";
        Query query = session.createQuery(getSubjectQuery);
        query.setParameter("name", name);
        query.setParameter("subjectType", subjectType);

        return (Subject) query.getSingleResult();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Subject> getAll() {
        Session session = sessionFactory.getCurrentSession();

        String getSubjectQuery = "from Subject";
        Query query = session.createQuery(getSubjectQuery);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getGroups(int subjectId) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select groups from Subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectId);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Teacher getTeacher(int subjectId) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select subject.teacher from Subject subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectId);

        return (Teacher) query.getSingleResult();
    }
}