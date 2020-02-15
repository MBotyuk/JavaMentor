package dao;

import org.hibernate.SessionFactory;
import util.DBHelper;
import util.PropertyReader;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicReference;

public class UserDaoFactory {

    private static SessionFactory sessionFactory;
    private static Connection connection;
    private static AtomicReference<UserHibernateDAO> userHibernateDAO;
    private static AtomicReference<UserJdbcDAO> userJdbcDAO;

    public UserDAO getDAO() {

        if (PropertyReader.getType().equals("hibernate")) {

            if (sessionFactory == null) {
                sessionFactory = DBHelper.createSessionFactory();
                userHibernateDAO = new AtomicReference<>(new UserHibernateDAO(sessionFactory));
            }
            return (UserDAO) userHibernateDAO.get();
        } else {

            if (connection == null) {
                connection = DBHelper.getConnection();
                userJdbcDAO = new AtomicReference<>(new UserJdbcDAO(connection));
            }
            return (UserDAO) userJdbcDAO.get();
        }
    }
}