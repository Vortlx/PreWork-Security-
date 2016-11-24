package prework.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.Group;
import prework.entities.Subject;
import prework.entities.SubjectType;
import prework.entities.Teacher;
import prework.dao.DAOSubject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DAOSubjectImpl implements DAOSubject {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(Subject subject) {
        entityManager.persist(subject);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addGroup(int subjectId, Group group) {
        Subject subject = entityManager.find(Subject.class, subjectId);
        subject.addGroup(group);
        group.addSubject(subject);

        entityManager.merge(subject);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeName(int subjectId, String newName) {
        Subject subject = entityManager.find(Subject.class, subjectId);
        subject.setName(newName);
        entityManager.merge(subject);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteById(int subjectId) {
        Subject subject = entityManager.find(Subject.class, subjectId);
        entityManager.remove(subject);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Subject getById(int subjectId) {
        return entityManager.find(Subject.class, subjectId);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Subject getByNameAndType(String name, SubjectType subjectType) {
        String getSubjectQuery = "from Subject where name = :name and type = :subjectType";
        Query query = entityManager.createQuery(getSubjectQuery);
        query.setParameter("name", name);
        query.setParameter("subjectType", subjectType);

        return (Subject) query.getSingleResult();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Subject> getAll() {
        String getSubjectQuery = "from Subject";
        Query query = entityManager.createQuery(getSubjectQuery);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getGroups(int subjectId) {
        String getGroupsQuery = "select groups from Subject where id = :id";
        Query query = entityManager.createQuery(getGroupsQuery);
        query.setParameter("id", subjectId);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Teacher getTeacher(int subjectId) {
        String getGroupsQuery = "select subject.teacher from Subject subject where id = :id";
        Query query = entityManager.createQuery(getGroupsQuery);
        query.setParameter("id", subjectId);

        return (Teacher) query.getSingleResult();
    }
}