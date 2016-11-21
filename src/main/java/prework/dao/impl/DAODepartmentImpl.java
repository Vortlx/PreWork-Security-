package prework.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.Department;
import prework.entities.Group;
import prework.entities.Teacher;
import prework.dao.DAODepartment;

import javax.persistence.Query;
import java.util.List;

@Repository
public class DAODepartmentImpl implements DAODepartment {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.save(department);
    }

    public void addGroup(int depId, Group group) {
        Session session = sessionFactory.getCurrentSession();

        Department department = session.get(Department.class, depId);
        department.addGroup(group);

        session.update(department);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeName(int depId, String newName) {
        Session session = sessionFactory.getCurrentSession();

        Department department = session.get(Department.class, depId);
        department.setName(newName);
        session.update(department);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delete(int depId) {
        Session session = sessionFactory.getCurrentSession();
        Department department = session.get(Department.class, depId);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Department getById(int depId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Department.class, depId);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Department getByName(String depName) {
        Session session = sessionFactory.getCurrentSession();

        String getDepByNameQuery = "from Department where name = :name";
        Query query = session.createQuery(getDepByNameQuery);
        query.setParameter("name", depName);

        return (Department) query.getSingleResult();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Group> getGroups(int depId) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select groups from Department where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", depId);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Group getGroup(int depId, String groupName) {
        Session session = sessionFactory.getCurrentSession();

        String getGroupsQuery = "select groups from Department where id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("id", depId);
        List<Group> groups = (List<Group>) query.getSingleResult();

        for (Group group : groups) {
            if (group.getName().equals(groupName)) {
                return group;
            }
        }

        return new Group();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Teacher> getTeachers(int depId) {
        Session session = sessionFactory.getCurrentSession();

        String getTeachersQuery = "select teachers from Department where id = :id";
        Query query = session.createQuery(getTeachersQuery);
        query.setParameter("id", depId);

        return query.getResultList();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Teacher getTeacher(int depId, String teacherName, String teacherFamilyName) {
        Session session = sessionFactory.getCurrentSession();

        String getTeacherByNameQuery = "select teachers from Department where id = :id";
        Query query = session.createQuery(getTeacherByNameQuery);
        query.setParameter("id", depId);
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