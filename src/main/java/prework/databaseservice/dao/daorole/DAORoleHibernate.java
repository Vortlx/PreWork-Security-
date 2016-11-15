package prework.databaseservice.dao.daorole;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prework.data.Role;
import prework.databaseservice.dao.DAORole;

import javax.persistence.Query;

@Component
public class DAORoleHibernate implements DAORole{

    @Autowired
    SessionFactory sessionFactory;

    public void add(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.save(role);

        session.getTransaction().commit();
    }

    public void delete(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.delete(role);

        session.getTransaction().commit();
    }

    public Role getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getRoleByNameQuery = "from Role where name = :name";
        Query query = session.createQuery(getRoleByNameQuery);
        query.setParameter("name", name);

        Role role = (Role) query.getSingleResult();

        session.getTransaction().commit();

        return role;
    }
}
