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
        session.delete(department);
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

        String getGroupsQuery = "select gr from Group gr inner join gr.department dep" +
                                    " where gr.name = :name and dep.id = :id";
        Query query = session.createQuery(getGroupsQuery);
        query.setParameter("name", groupName);
        query.setParameter("id", depId);

        return (Group) query.getSingleResult();
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

        String getTeacherByNameQuery = "select teacher from Teacher teacher inner join teacher.department dep" +
                                    " where teacher.name = :name and teacher.familyName = :familyName" +
                                    " and dep.id = :id";
        Query query = session.createQuery(getTeacherByNameQuery);
        query.setParameter("name", teacherName);
        query.setParameter("familyName", teacherFamilyName);
        query.setParameter("id", depId);

        return (Teacher) query.getSingleResult();
    }
}