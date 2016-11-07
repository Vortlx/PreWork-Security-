package prework.databaseservice.dao.daogroup;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import prework.databaseservice.dao.DAOGroup;
import prework.data.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("daoGroupHibernate")
public class DAOGroupHibernate implements DAOGroup {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(String name) throws SQLException {
        
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        
        Group group = new Group();
        group.setName(name);
        session.save(group);
        
        session.getTransaction().commit();
    }

    public void update(int groupID, String newName) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String queryString = "update Group set name = :name where id = :id";
        Query query = session.createQuery(queryString);
        query.setParameter("name", newName);
        query.setParameter("id", groupID);
        
        session.getTransaction().commit();
    }

    public void delete(String name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        
        String queryString = "delete Group where name=:name";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        query.executeUpdate();
        
        session.getTransaction().commit();
    }

    public Group getByName(String name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        
        String queryString = "from Group where name=:name";
        Query query = session.createQuery(queryString);
        query.setParameter("name", name);
        Group group = (Group) query.getSingleResult();
        
        session.getTransaction().commit();
        
        return group;
    }

    public List<Group> getAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        
        String queryString = "from Group";
        Query query = session.createQuery(queryString);
        List<Group> groups = query.getResultList();
        
        session.getTransaction().commit();
        
        return groups;
    }
}
