package prework.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.Department;
import prework.entities.Group;
import prework.entities.Teacher;
import prework.dao.DAODepartment;

import javax.persistence.Query;
import java.util.List;

@Component
public class DAODepartmentHibernate implements DAODepartment {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.save(department);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeName(int depID, String newName) {
        Session session = sessionFactory.getCurrentSession();

        Department department = session.get(Department.class, depID);
        department.setName(newName);
        session.update(department);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeLogin(int depID, String newLogin) {
        Session session = sessionFactory.getCurrentSession();

        Department department = session.get(Department.class, depID);
        session.update(department);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changePassword(int depID, String newPassword) {
        Session session = sessionFactory.getCurrentSession();

        Department department = session.get(Department.class, depID);
        session.update(department);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delete(int depID) {
        Session session = sessionFactory.getCurrentSession();

        Department department = session.get(Department.class, depID);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Department getByID(int depID) {
        Session session = sessionFactory.getCurrentSession();
        Department department = session.get(Department.class, depID);
        return department;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Department getByName(String depName) {
        Session session = sessionFactory.getCurrentSession();

        String getDepByNameQuery = "from Department where name = :name";
        Query query = session.createQuery(getDepByNameQuery);
        query.setParameter("name", depName);

        Department department = (Department) query.getSingleResult();

        return department;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getGroups(int depID) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select groups from Department where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", depID);
        List<Group> groups = (List<Group>) query.getSingleResult();

        return groups;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getGroup(int depID, String groupName) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select groups from Department where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", depID);
        List<Group> groups = (List<Group>) query.getSingleResult();

        for (Group group : groups) {
            if (group.getName().equals(groupName)) {
                return group;
            }
        }

        return new Group();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Teacher> getTeachers(int depID) {
        Session session = sessionFactory.getCurrentSession();

        String getTeachersQuery = "select teachers from Department where id = :id";
        Query query = session.createQuery(getTeachersQuery);
        query.setParameter("id", depID);

        List<Teacher> teachers = (List<Teacher>) query.getSingleResult();

        return teachers;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Teacher getTeacher(int depID, String teacherName, String teacherFamilyName) {
        Session session = sessionFactory.getCurrentSession();

        String getTeacherByNameQuery = "select teachers from Department where id = :id";
        Query query = session.createQuery(getTeacherByNameQuery);
        query.setParameter("id", depID);
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