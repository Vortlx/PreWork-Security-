package prework.databaseservice.dao.daogroup;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import prework.data.Department;
import prework.data.Student;
import prework.data.Subject;
import prework.databaseservice.dao.DAOGroup;
import prework.data.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("daoGroupHibernate")
public class DAOGroupHibernate implements DAOGroup {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(String name, Department department) {
        
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Group group = new Group();
        group.setName(name);
        group.setDepartment(department);
        session.save(group);
        
        session.getTransaction().commit();
    }

    public void addStudent(int groupID, Student student){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Group group = session.get(Group.class, groupID);
        group.addStudent(student);
        session.update(group);

        session.getTransaction().commit();
    }

    public void addSubject(int groupID, Subject subject) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Group group = session.get(Group.class, groupID);
        group.addSubject(subject);
        session.update(group);

        session.getTransaction().commit();
    }

    public void changeName(int groupID, String newName) {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String queryString = "update Group set name = :name where id = :id";
        Query query = session.createQuery(queryString);
        query.setParameter("name", newName);
        query.setParameter("id", groupID);
        
        session.getTransaction().commit();
    }

    public void deleteByID(int groupID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        
        Group group = session.get(Group.class, groupID);
        session.delete(group);
        
        session.getTransaction().commit();
    }

    public void deleteByName(String groupName){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getGroupByNameQuery = "from Group where name = :name";
        Query query = session.createQuery(getGroupByNameQuery);
        query.setParameter("name", groupName);
        Group group = (Group) query.getSingleResult();
        session.delete(group);

        session.getTransaction().commit();
    }

    public Group getByID(int groupID){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Group group = session.get(Group.class, groupID);

        session.getTransaction().commit();

        return group;
    }

    public Group getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        
        String queryString = "from Group where name=:name";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        Group group = (Group) query.getSingleResult();
        
        session.getTransaction().commit();
        
        return group;
    }

    public List<Group> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        
        String queryString = "from Group";
        Query query = session.createQuery(queryString);
        List<Group> groups = query.getResultList();
        
        session.getTransaction().commit();
        
        return groups;
    }

    public List<Student> getStudents(int groupID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getStudentsQuery = "select students from Group where id = :id";
        Query query = session.createQuery(getStudentsQuery);
        query.setParameter("id", groupID);
        List<Student> students = (List<Student>) query.getSingleResult();

        session.getTransaction().commit();

        return students;
    }

    public Student getStudent(int groupID, String studentName, String studentFamilyName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getStudentsQuery = "select students from Group where id = :id";
        Query query = session.createQuery(getStudentsQuery);
        query.setParameter("id", groupID);
        List<Student> students = (List<Student>) query.getSingleResult();

        session.getTransaction().commit();

        for(Student student: students){
            if(student.getName().equals(studentName) &&
                    student.getName().equals(studentFamilyName)){
                return student;
            }
        }

        return new Student();
    }

    public List<Subject> getSubjects(int groupID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getSubjectsQuery = "select subjects from Group where id = :id";
        Query query = session.createQuery(getSubjectsQuery);
        query.setParameter("id", groupID);
        List<Subject> subjects = (List<Subject>) query.getSingleResult();

        session.getTransaction().commit();

        return subjects;
    }
}