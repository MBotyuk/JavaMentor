package dao;

import org.hibernate.SessionFactory;
import util.DBHelper;

import java.sql.Connection;

public class UserDaoFactory {

    private static SessionFactory sessionFactory;
    private static Connection connection;

    public UserDAO getDAO() {
        if (DBHelper.getTypeDAO().equals("hibernate")) {
            if (sessionFactory == null) {
                sessionFactory = DBHelper.getConnectionOrSessionFactory();
            }
            return new UserHibernateDAO(sessionFactory.openSession());
        } else {
            if (connection == null) {
                connection = DBHelper.getConnectionOrSessionFactory();
            }
            return new UserJdbcDAO(connection);
        }
    }
}
