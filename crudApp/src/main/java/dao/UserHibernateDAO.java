package dao;

import model.User;
import org.hibernate.*;

import java.util.List;

public class UserHibernateDAO implements UserDAO<User> {
    private final Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    public List<User> getAllUser() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    public User getUser(long id) {
        Transaction transaction = session.beginTransaction();

        //User user = session.createQuery("FROM User where id= " + id + "");

        User user = (User) session.get(User.class, id);
        transaction.commit();
        session.close();
        return user;
    }

    public boolean addUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    public long getNumberOfUserInTable() {
        Transaction transaction = session.beginTransaction();
        long result = (long) session.createQuery("select count(*) from User").uniqueResult();
        transaction.commit();
        session.close();
        return result;
    }

//    public long isUser(User user) {
//            Transaction transaction = session.beginTransaction();
//            long result = (long) session.createQuery("FROM " + user + "").uniqueResult();
//            transaction.commit();
//            session.close();
//            return result;
//    }

    public long isUser(User user) {
        Query query = session.createQuery("select 1 from User t where t.email = :email");
        query.setString("email", user.getEmail());

        if (query.uniqueResult() != null) {
            return 1;
        }
        return 0;
    }

    public void createTable() {

    }

    public void clearTable() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
        session.close();
    }

    public void delUser(long id) {
        Transaction transaction = session.beginTransaction();
        session.delete(new UserDaoFactory().getDAO().getUser(id));
        transaction.commit();
        session.close();
    }

    @Override
    public void editUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }
}
