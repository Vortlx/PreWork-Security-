package prework.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.Role;
import prework.entities.User;
import prework.dao.DAOUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class DAOUserImpl implements DAOUser {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(User user) throws Exception {
        User existingUser = entityManager.find(User.class, user.getId());

        if (existingUser != null) {
            throw new Exception();
        }

        entityManager.persist(user);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteById(int userId) {
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
    }

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
    public User getById(int userId) {
        return entityManager.find(User.class, userId);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public User getByUsername(String username) {
        String getUserByUsernameQuery = "from User where username = :username";
        Query query = entityManager.createQuery(getUserByUsernameQuery);
        query.setParameter("username", username);

        return (User) query.getSingleResult();
    }
}