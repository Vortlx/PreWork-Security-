package prework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import prework.entities.Group;
import prework.entities.Subject;
import prework.entities.SubjectType;
import prework.entities.Teacher;
import prework.dao.crudinterface.DAOSubject;
import prework.dao.DAOSubjectCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DAOSubjectImpl implements DAOSubjectCustom {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private DAOSubject daoSubject;

    public void addGroup(int subjectId, Group group) {
        Subject subject = entityManager.find(Subject.class, subjectId);
        subject.addGroup(group);
        group.addSubject(subject);

        entityManager.merge(subject);
    }

    public void changeName(int subjectId, String newName) {
        Subject subject = entityManager.find(Subject.class, subjectId);
        subject.setName(newName);
        entityManager.merge(subject);
    }

    public Subject getByNameAndType(String name, SubjectType subjectType) {
        String getSubjectQuery = "from Subject where name = :name and type = :subjectType";
        Query query = entityManager.createQuery(getSubjectQuery);
        query.setParameter("name", name);
        query.setParameter("subjectType", subjectType);

        return (Subject) query.getSingleResult();
    }

    public List<Group> getGroups(int subjectId) {
        String getGroupsQuery = "select groups from Subject where id = :id";
        Query query = entityManager.createQuery(getGroupsQuery);
        query.setParameter("id", subjectId);

        return query.getResultList();
    }

    public Teacher getTeacher(int subjectId) {
        String getGroupsQuery = "select subject.teacher from Subject subject where id = :id";
        Query query = entityManager.createQuery(getGroupsQuery);
        query.setParameter("id", subjectId);

        return (Teacher) query.getSingleResult();
    }
}