package dao;

import model.User;
import org.hibernate.*;

import java.util.List;

public class UserHibernateDAO implements UserDAO<User> {
    private Session session = null;
    private final SessionFactory sessionFactory;

    public UserHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUser() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public User getUser(long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public boolean addUser(User user) {
        session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {

            }
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public long getNumberOfUserInTable() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        long result = (long) session.createQuery("select count(*) from User").uniqueResult();
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public long isUserByEmail(User user) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("select 1 from User t where t.email = :email");
        query.setString("email", user.getEmail());

        if (query.uniqueResult() != null) {
            session.close();
            return 1;
        }
        session.close();
        return 0;
    }

    @Override
    public void createTable() {

    }

    @Override
    public void clearTable() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void delUser(long id) {
        User user = getUser(id);
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public boolean editUser(User user) {
        session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {

            }
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public User getUserByEmailPassword(String email, String password) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from User where email = :email");
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();

        session.close();
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}