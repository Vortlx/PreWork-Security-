package prework.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import prework.entities.Student;
import prework.entities.Subject;
import prework.dao.crudinterface.DAOGroup;
import prework.dao.DAOGroupCustom;
import prework.entities.Group;


@Repository
public class DAOGroupImpl implements DAOGroupCustom {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private DAOGroup daoGroup;

    public void addStudent(int groupID, Student student) {
        Group group = entityManager.find(Group.class, groupID);
        group.addStudent(student);
        entityManager.merge(group);
    }

    public void addSubject(int groupID, Subject subject) {
        Group group = entityManager.find(Group.class, groupID);
        group.addSubject(subject);
        subject.addGroup(group);

        entityManager.merge(group);
    }


    public void deleteByName(String groupName) {
        String getGroupByNameQuery = "from Group where name = :name";
        Query query = entityManager.createQuery(getGroupByNameQuery);
        query.setParameter("name", groupName);
        Group group = (Group) query.getSingleResult();
        entityManager.remove(group);
    }

    public void deleteSubject(int groupId, int subjectId) {
        Subject subject = entityManager.find(Subject.class, subjectId);
        Group group = entityManager.find(Group.class, groupId);

        subject.deleteGroup(group);
        group.deleteSubject(subject);

        entityManager.merge(group);
    }

    public Group getByName(String name) {
        String queryString = "from Group where name=:name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);

        return (Group) query.getSingleResult();
    }

    public List<Student> getStudents(int groupID) {
        String getStudentsQuery = "select students from Group where id = :id";
        Query query = entityManager.createQuery(getStudentsQuery);
        query.setParameter("id", groupID);

        return query.getResultList();
    }

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

    public List<Subject> getSubjects(int groupID) {
        String getSubjectsQuery = "select subjects from Group where id = :id";
        Query query = entityManager.createQuery(getSubjectsQuery);
        query.setParameter("id", groupID);

        return query.getResultList();
    }
}