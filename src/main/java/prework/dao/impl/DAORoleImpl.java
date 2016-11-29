package prework.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import prework.entities.Role;
import prework.dao.crudinterface.DAORole;
import prework.dao.DAORoleCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class DAORoleImpl implements DAORoleCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DAORole daoRole;
    

    public Role getByName(String name) {
        String getRoleByNameQuery = "from Role where name = :name";
        Query query = entityManager.createQuery(getRoleByNameQuery);
        query.setParameter("name", name);

        return (Role) query.getSingleResult();
    }
}
