package prework.databaseservice.dao.daodepartment;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prework.data.Department;
import prework.data.Group;
import prework.data.Teacher;
import prework.databaseservice.dao.DAODepartment;

import javax.persistence.Query;
import java.util.List;

@Component
public class DAODepartmentHibernate implements DAODepartment{

    @Autowired
    private SessionFactory sessionFactory;

    public void add(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.save(department);

        session.getTransaction().commit();
    }

    public void changeName(int depID, String newName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Department department = session.get(Department.class, depID);
        department.setName(newName);
        session.update(department);

        session.getTransaction().commit();
    }

    public void changeLogin(int depID, String newLogin) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Department department = session.get(Department.class, depID);
        session.update(department);

        session.getTransaction().commit();
    }

    public void changePassword(int depID, String newPassword) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Department department = session.get(Department.class, depID);
        session.update(department);

        session.getTransaction().commit();
    }

    public void delete(int depID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Department department = session.get(Department.class, depID);
        session.delete(department);

        session.getTransaction().commit();
    }

    public Department getBiID(int depID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Department department = session.get(Department.class, depID);

        session.getTransaction().commit();

        return department;
    }

    public Department getByName(String depName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getDepByNameQuery = "from Department where name = :name";
        Query query = session.createQuery(getDepByNameQuery);
        query.setParameter("name", depName);

        Department department = (Department) query.getSingleResult();
        session.getTransaction().commit();

        return department;
    }

    public List<Group> getGroups(int depID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getGroupsQuery = "select groups from Department where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", depID);
        List<Group> groups = (List<Group>) query.getSingleResult();

        session.getTransaction().commit();

        return groups;
    }

    public Group getGroup(int depID, String groupName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getGroupsQuery = "select groups from Department where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", depID);
        List<Group> groups = (List<Group>) query.getSingleResult();

        session.getTransaction().commit();

        for(Group group: groups){
            if(group.getName().equals(groupName)){
                return group;
            }
        }

        return new Group();
    }

    public List<Teacher> getTeachers(int depID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getTeachersQuery = "select teachers from Department where id = :id";
        Query query = session.createQuery(getTeachersQuery);
        query.setParameter("id", depID);

        List<Teacher> teachers = (List<Teacher>) query.getSingleResult();

        session.getTransaction().commit();

        return teachers;
    }

    public Teacher getTeacher(int depID, String teacherName, String teacherFamilyName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getTeacherByNameQuery = "select teachers from Department where id = :id";
        Query query = session.createQuery(getTeacherByNameQuery);
        query.setParameter("id", depID);
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