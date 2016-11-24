package prework.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.Role;
import prework.dao.DAORole;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class DAORoleImpl implements DAORole {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delete(Role role) {
        entityManager.remove(role);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public Role getByName(String name) {
        String getRoleByNameQuery = "from Role where name = :name";
        Query query = entityManager.createQuery(getRoleByNameQuery);
        query.setParameter("name", name);

        return (Role) query.getSingleResult();
    }
}
