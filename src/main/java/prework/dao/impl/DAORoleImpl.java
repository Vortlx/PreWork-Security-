package prework.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.Role;
import prework.dao.DAORole;

import javax.persistence.Query;

@Component
public class DAORoleImpl implements DAORole {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(Role role) {
        Session session = sessionFactory.getCurrentSession();

        session.save(role);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delete(Role role) {
        Session session = sessionFactory.getCurrentSession();

        session.delete(role);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Role getByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        String getRoleByNameQuery = "from Role where name = :name";
        Query query = session.createQuery(getRoleByNameQuery);
        query.setParameter("name", name);

        return (Role) query.getSingleResult();
    }
}
