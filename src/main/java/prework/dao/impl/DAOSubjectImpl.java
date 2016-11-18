package prework.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.Group;
import prework.entities.Subject;
import prework.entities.SubjectType;
import prework.entities.Teacher;
import prework.dao.DAOSubject;

import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
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
    public void changeName(int subjectID, String newName) {
        Session session = sessionFactory.getCurrentSession();

        Subject subject = session.get(Subject.class, subjectID);
        subject.setName(newName);
        session.update(subject);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delete(int subjectID) {
        Session session = sessionFactory.getCurrentSession();

        Subject subject = session.get(Subject.class, subjectID);
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
    public List<Group> getGroups(int subjectID) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select groups from Subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectID);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getGroup(int subjectID, String groupName) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select groups from Subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectID);
        List<Group> groups = query.getResultList();

        for (Group group : groups) {
            if (group.getName().equals(groupName)) {
                return group;
            }
        }

        return new Group();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Teacher> getTeachers(int subjectID) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select teachers from Subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectID);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Teacher getTeacher(int subjectID, String teacherName, String teacherFamilyName) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select teachers from Subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectID);
        List<Teacher> teachers = query.getResultList();

        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(teacherName) &&
                    teacher.getName().equals(teacherFamilyName)) {
                return teacher;
            }
        }

        return new Teacher();
    }
}