package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper <T> {

    private static SessionFactory sessionFactory;
    private static Connection connection;
    static String typeDAO;

    static {
        File file = new File("C:\\Users\\mboty\\GitHub\\JavaMentor\\crudApp\\src\\main\\resources\\db.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        typeDAO = properties.getProperty("typeDAO");
    }

    public static <T> T getConnectionOrSessionFactory() {

        if (typeDAO.equals("hibernate")) {
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

    public static String getTypeDAO() {
        return typeDAO;
    }

    @SuppressWarnings("UnusedDeclaration")
    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mysqlCRUD?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "folk1987");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        return configuration.buildSessionFactory(serviceRegistry);
    }


    private static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").                //db type
                    append("localhost:").                   //host name
                    append("3306/").                        //port
                    append("mysqlCRUD?serverTimezone=UTC&").//db name
                    append("user=root&").                   //login
                    append("password=folk1987");            //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
