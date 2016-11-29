package prework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import prework.entities.Department;
import prework.entities.Group;
import prework.entities.Teacher;
import prework.dao.crudinterface.DAODepartment;
import prework.dao.DAODepartmentCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DAODepartmentImpl implements DAODepartmentCustom {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private DAODepartment daoDepartment;

    public void addGroup(int depId, Group group) {
        Department department = entityManager.find(Department.class, depId);
        department.addGroup(group);

        entityManager.merge(department);
    }

    public void changeName(int depId, String newName) {
        Department department = entityManager.find(Department.class, depId);
        department.setName(newName);
        entityManager.merge(department);
    }

    public Department getByName(String depName) {
        String getDepByNameQuery = "from Department where name = :name";
        Query query = entityManager.createQuery(getDepByNameQuery);
        query.setParameter("name", depName);

        return (Department) query.getSingleResult();
    }

    public List<Group> getGroups(int depId) {
        String getGroupsQuery = "select groups from Department where id = :id";
        Query query = entityManager.createQuery(getGroupsQuery);
        query.setParameter("id", depId);

        return query.getResultList();
    }

    public Group getGroup(int depId, String groupName) {
        String getGroupsQuery = "select gr from Group gr inner join gr.department dep" +
                                    " where gr.name = :name and dep.id = :id";
        Query query = entityManager.createQuery(getGroupsQuery);
        query.setParameter("name", groupName);
        query.setParameter("id", depId);

        return (Group) query.getSingleResult();
    }

    public List<Teacher> getTeachers(int depId) {
        String getTeachersQuery = "select teachers from Department where id = :id";
        Query query = entityManager.createQuery(getTeachersQuery);
        query.setParameter("id", depId);

        return query.getResultList();
    }

    public Teacher getTeacher(int depId, String teacherName, String teacherFamilyName) {
        String getTeacherByNameQuery = "select teacher from Teacher teacher inner join teacher.department dep" +
                                    " where teacher.name = :name and teacher.familyName = :familyName" +
                                    " and dep.id = :id";
        Query query = entityManager.createQuery(getTeacherByNameQuery);
        query.setParameter("name", teacherName);
        query.setParameter("familyName", teacherFamilyName);
        query.setParameter("id", depId);

        return (Teacher) query.getSingleResult();
    }
}