package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;

public class DBHelper<T> {

    private static SessionFactory sessionFactory;
    private static Connection connection;

    public static <T> T getConnectionOrSessionFactory() {

        if (PropertyReader.getType().equals("hibernate")) {
            if (sessionFactory == null) {
                sessionFactory = createSessionFactory();
            }
            return (T) sessionFactory;
        } else {
            if (connection == null) {
                connection = getConnection();
            }
            return (T) connection;
        }
    }

    public static SessionFactory createSessionFactory() {
        Configuration configuration = PropertyReader.getConfig();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static Connection getConnection() {
        return PropertyReader.getConnect();
    }
}