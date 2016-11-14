package prework.databaseservice.dao.daouserinfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prework.data.Role;
import prework.data.UserInfo;
import prework.databaseservice.dao.DAOUserInfo;

import javax.persistence.Query;

@Component
public class DAOUserInfoHibernate implements DAOUserInfo {

    @Autowired
    SessionFactory sessionFactory;

    public void add(UserInfo userInfo) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        UserInfo existingUser = session.get(UserInfo.class, userInfo.getId());
        if(existingUser != null)
            throw new Exception();

        session.save(userInfo);

        session.getTransaction().commit();
    }

    public void deleteByID(int userInfoID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        UserInfo userInfo = session.get(UserInfo.class, userInfoID);
        session.delete(userInfo);

        session.getTransaction().commit();
    }

    public void delelteByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getUserInfoByUsernameQuery = "from UserInfo where username = :username";
        Query query = session.createQuery(getUserInfoByUsernameQuery);
        query.setParameter("username", username);
        UserInfo userInfo = (UserInfo) query.getSingleResult();
        session.delete(userInfo);


        session.getTransaction().commit();
    }

    public void changePassword(int userInfoID, String newPassword) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        UserInfo userInfo = session.get(UserInfo.class, userInfoID);
        userInfo.setPassword(newPassword);
        session.update(userInfo);

        session.getTransaction().commit();
    }

    public void changeUsername(int userInfoID, String newUsername) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        UserInfo existingUser = getByUsername(newUsername);

        if(existingUser != null)
            throw new Exception();

        UserInfo userInfo = session.get(UserInfo.class, userInfoID);
        userInfo.setUsername(newUsername);
        session.update(userInfo);

        session.getTransaction().commit();
    }

    public void changeEnabled(int userInfoID, int newEnabled) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        UserInfo userInfo = session.get(UserInfo.class, userInfoID);
        userInfo.setEnabled(newEnabled);
        session.update(userInfo);

        session.getTransaction().commit();
    }

    public void changeRole(int userInfoID, Role newRole) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        UserInfo userInfo = session.get(UserInfo.class, userInfoID);
        userInfo.setRole(newRole);
        session.update(userInfo);

        session.getTransaction().commit();
    }

    public UserInfo getByID(int userInfoID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        UserInfo userInfo = session.get(UserInfo.class, userInfoID);

        session.getTransaction().commit();

        return userInfo;
    }

    public UserInfo getByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String getUserInfoByUsernameQuery = "from UserInfo where username = :username";
        Query query = session.createQuery(getUserInfoByUsernameQuery);
        query.setParameter("username", username);
        UserInfo userInfo = (UserInfo) query.getSingleResult();

        session.getTransaction().commit();

        return userInfo;
    }
}