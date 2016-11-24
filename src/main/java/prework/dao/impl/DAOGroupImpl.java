package prework.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.Student;
import prework.entities.Subject;
import prework.dao.DAOGroup;
import prework.entities.Group;


@Repository
public class DAOGroupImpl implements DAOGroup {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(Group group) {
        entityManager.persist(group);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addStudent(int groupID, Student student) {
        Group group = entityManager.find(Group.class, groupID);
        group.addStudent(student);
        entityManager.merge(group);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addSubject(int groupID, Subject subject) {
        Group group = entityManager.find(Group.class, groupID);
        group.addSubject(subject);
        subject.addGroup(group);

        entityManager.merge(group);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByID(int groupID) {
        Group group = entityManager.find(Group.class, groupID);
        entityManager.remove(group);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByName(String groupName) {
        String getGroupByNameQuery = "from Group where name = :name";
        Query query = entityManager.createQuery(getGroupByNameQuery);
        query.setParameter("name", groupName);
        Group group = (Group) query.getSingleResult();
        entityManager.remove(group);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteSubject(int groupId, int subjectId) {
        Subject subject = entityManager.find(Subject.class, subjectId);
        Group group = entityManager.find(Group.class, groupId);

        subject.deleteGroup(group);
        group.deleteSubject(subject);

        entityManager.merge(group);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getById(int groupId) {
        return entityManager.find(Group.class, groupId);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getByName(String name) {
        String queryString = "from Group where name=:name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);

        return (Group) query.getSingleResult();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getAll() {
        String queryString = "from Group";
        Query query = entityManager.createQuery(queryString);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getStudents(int groupID) {
        String getStudentsQuery = "select students from Group where id = :id";
        Query query = entityManager.createQuery(getStudentsQuery);
        query.setParameter("id", groupID);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Student getStudent(int groupID, String studentName, String studentFamilyName) {
        String getStudentsQuery = "select student from Student student inner join student.group gr where" +
                                    " student.name = :name and student.familyName = :familyName " +
                                    "and gr.id = :id";
        Query query = entityManager.createQuery(getStudentsQuery);
        query.setParameter("name", studentName);
        query.setParameter("familyName", studentFamilyName);
        query.setParameter("id", groupID);
        Student student = (Student) query.getSingleResult();

        return student;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Subject> getSubjects(int groupID) {
        String getSubjectsQuery = "select subjects from Group where id = :id";
        Query query = entityManager.createQuery(getSubjectsQuery);
        query.setParameter("id", groupID);

        return query.getResultList();
    }
}