package prework.databaseservice.dao.daosubject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prework.data.Group;
import prework.data.Subject;
import prework.data.SubjectType;
import prework.data.Teacher;
import prework.databaseservice.dao.DAOSubject;

import javax.persistence.Query;
import java.util.List;

@Component
public class DAOSubjectHibernate implements DAOSubject {

    @Autowired
    SessionFactory sessionFactory;


    public void add(Subject subject) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.save(subject);

        session.getTransaction().commit();
    }

    public void changeName(int subjectID, String newName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Subject subject = session.get(Subject.class, subjectID);
        subject.setName(newName);
        session.update(subject);

        session.getTransaction().commit();
    }

    public void delete(int subjectID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Subject subject = session.get(Subject.class, subjectID);
        session.delete(subject);

        session.getTransaction().commit();
    }

    public Subject getByNameAndType(String name, SubjectType subjectType){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        
        String getSubjectQuery = "from Subject where name = :name and type = :subjectType";
        Query query = session.createQuery(getSubjectQuery);
        query.setParameter("name", name);
        query.setParameter("subjectType", subjectType);
        Subject subject = (Subject) query.getSingleResult();
        
        session.getTransaction().commit();
        
        return subject;
    }
    
    public List<Group> getGroups(int subjectID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getGroupsQuery = "select groups from Subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectID);
        List<Group> groups = (List<Group>) query.getSingleResult();

        session.getTransaction().commit();

        return groups;
    }

    public Group getGroup(int subjectID, String groupName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getGroupsQuery = "select groups from Subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectID);
        List<Group> groups = (List<Group>) query.getSingleResult();

        session.getTransaction().commit();

        for(Group group: groups){
            if(group.getName().equals(groupName)){
                return group;
            }
        }

        return new Group();
    }

    public List<Teacher> getTeachers(int subjectID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getGroupsQuery = "select teachers from Subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectID);
        List<Teacher> teachers = (List<Teacher>) query.getSingleResult();

        session.getTransaction().commit();

        return teachers;
    }

    public Teacher getTeacher(int subjectID, String teacherName, String teacherFamilyName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getGroupsQuery = "select teachers from Subject where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", subjectID);
        List<Teacher> teachers = (List<Teacher>) query.getSingleResult();

        session.getTransaction().commit();

        for(Teacher teacher: teachers){
            if(teacher.getName().equals(teacherName) &&
                    teacher.getName().equals(teacherFamilyName)){
                return teacher;
            }
        }

        return new Teacher();
    }
}