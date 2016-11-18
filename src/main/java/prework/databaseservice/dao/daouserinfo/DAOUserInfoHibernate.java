package prework.databaseservice.dao.daouserinfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.data.Role;
import prework.data.UserInfo;
import prework.databaseservice.dao.DAOUserInfo;

import javax.persistence.Query;

@Component
public class DAOUserInfoHibernate implements DAOUserInfo {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void add(UserInfo userInfo) throws Exception {
        Session session = sessionFactory.getCurrentSession();

        UserInfo existingUser = session.get(UserInfo.class, userInfo.getId());

        if (existingUser != null) {
            session.getTransaction().commit();
            throw new Exception();
        }

        session.save(userInfo);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByID(int userInfoID) {
        Session session = sessionFactory.getCurrentSession();

        UserInfo userInfo = session.get(UserInfo.class, userInfoID);
        session.delete(userInfo);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delelteByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        String getUserInfoByUsernameQuery = "from UserInfo where username = :username";
        Query query = session.createQuery(getUserInfoByUsernameQuery);
        query.setParameter("username", username);
        UserInfo userInfo = (UserInfo) query.getSingleResult();
        session.delete(userInfo);

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changePassword(int userInfoID, String newPassword) {
        Session session = sessionFactory.getCurrentSession();

        UserInfo userInfo = session.get(UserInfo.class, userInfoID);
        userInfo.setPassword(newPassword);
        session.update(userInfo);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeUsername(int userInfoID, String newUsername) throws Exception {
        Session session = sessionFactory.getCurrentSession();

        UserInfo userInfo = session.get(UserInfo.class, userInfoID);
        userInfo.setUsername(newUsername);
        session.update(userInfo);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeEnabled(int userInfoID, int newEnabled) {
        Session session = sessionFactory.getCurrentSession();

        UserInfo userInfo = session.get(UserInfo.class, userInfoID);
        userInfo.setEnabled(newEnabled);
        session.update(userInfo);

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeRole(int userInfoID, Role newRole) {
        Session session = sessionFactory.getCurrentSession();

        UserInfo userInfo = session.get(UserInfo.class, userInfoID);
        userInfo.setRole(newRole);
        session.update(userInfo);

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public UserInfo getByID(int userInfoID) {
        Session session = sessionFactory.getCurrentSession();

        UserInfo userInfo = session.get(UserInfo.class, userInfoID);

        return userInfo;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS, readOnly = true)
    public UserInfo getByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        String getUserInfoByUsernameQuery = "from UserInfo where username = :username";
        Query query = session.createQuery(getUserInfoByUsernameQuery);
        query.setParameter("username", username);
        UserInfo userInfo = (UserInfo) query.getSingleResult();

        return userInfo;
    }
}