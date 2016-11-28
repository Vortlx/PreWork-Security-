package prework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.Role;
import prework.entities.User;
import prework.dao.crudinterface.DAOUser;
import prework.dao.DAOUserCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class DAOUserImpl implements DAOUserCustom {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private DAOUser daoUser;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delelteByUsername(String username) {
        String getUserByUsernameQuery = "from User where username = :username";
        Query query = entityManager.createQuery(getUserByUsernameQuery);
        query.setParameter("username", username);
        User user = (User) query.getSingleResult();
        entityManager.remove(user);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changePassword(int userID, String newPassword) {
        User user = entityManager.find(User.class, userID);
        user.setPassword(newPassword);
        entityManager.merge(user);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeUsername(int userID, String newUsername) throws Exception {
        User user = entityManager.find(User.class, userID);
        user.setUsername(newUsername);
        entityManager.merge(user);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeEnabled(int userID, int newEnabled) {
        User user = entityManager.find(User.class, userID);
        user.setEnabled(newEnabled);
        entityManager.merge(user);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeRole(int userID, Role newRole) {
        User user = entityManager.find(User.class, userID);
        user.setRole(newRole);
        entityManager.merge(user);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public User getByUsername(String username) {
        String getUserByUsernameQuery = "from User where username = :username";
        Query query = entityManager.createQuery(getUserByUsernameQuery);
        query.setParameter("username", username);

        return (User) query.getSingleResult();
    }
}