package prework.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.entities.Role;
import prework.entities.User;
import prework.dao.DAOUser;

import javax.persistence.Query;

@Component
public class DAOUserHibernate implements DAOUser {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(User user) throws Exception {
        Session session = sessionFactory.getCurrentSession();

        User existingUser = session.get(User.class, user.getId());

        if (existingUser != null) {
            session.getTransaction().commit();
            throw new Exception();
        }

        session.save(user);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByID(int userID) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, userID);
        session.delete(user);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delelteByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        String getUserByUsernameQuery = "from User where username = :username";
        Query query = session.createQuery(getUserByUsernameQuery);
        query.setParameter("username", username);
        User user = (User) query.getSingleResult();
        session.delete(user);

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changePassword(int userID, String newPassword) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, userID);
        user.setPassword(newPassword);
        session.update(user);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeUsername(int userID, String newUsername) throws Exception {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, userID);
        user.setUsername(newUsername);
        session.update(user);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeEnabled(int userID, int newEnabled) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, userID);
        user.setEnabled(newEnabled);
        session.update(user);

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeRole(int userID, Role newRole) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, userID);
        user.setRole(newRole);
        session.update(user);

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public User getByID(int userID) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, userID);

        return user;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public User getByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        String getUserByUsernameQuery = "from User where username = :username";
        Query query = session.createQuery(getUserByUsernameQuery);
        query.setParameter("username", username);
        User user = (User) query.getSingleResult();

        return user;
    }
}