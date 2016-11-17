package prework.databaseservice.dao.daosubject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.data.Group;
import prework.data.Subject;
import prework.data.SubjectType;
import prework.data.Teacher;
import prework.databaseservice.dao.DAOSubject;

import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DAOSubjectHibernate implements DAOSubject {

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

        Subject subject = session.get(Subject.class, subjectId);

        return subject;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Subject getByNameAndType(String name, SubjectType subjectType) {
        Session session = sessionFactory.getCurrentSession();

        String getSubjectQuery = "from Subject where name = :name and type = :subjectType";
        Query query = session.createQuery(getSubjectQuery);
        query.setParameter("name", name);
        query.setParameter("subjectType", subjectType);
        Subject subject = (Subject) query.getSingleResult();

        return subject;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<Subject> getAll() {
        Session session = sessionFactory.getCurrentSession();

        String getSubjectQuery = "from Subject";
        Query query = session.createQuery(getSubjectQuery);
        Set<Subject> subjects = new HashSet<Subject>();
        subjects.addAll(query.getResultList());

        return subjects;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getGroups(int subjectID) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select groups from Subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectID);
        List<Group> groups = (List<Group>) query.getSingleResult();

        return groups;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getGroup(int subjectID, String groupName) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select groups from Subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectID);
        List<Group> groups = (List<Group>) query.getSingleResult();

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
        List<Teacher> teachers = (List<Teacher>) query.getSingleResult();

        return teachers;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Teacher getTeacher(int subjectID, String teacherName, String teacherFamilyName) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select teachers from Subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectID);
        List<Teacher> teachers = (List<Teacher>) query.getSingleResult();

        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(teacherName) &&
                    teacher.getName().equals(teacherFamilyName)) {
                return teacher;
            }
        }

        return new Teacher();
    }
}