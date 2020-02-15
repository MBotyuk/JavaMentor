package util;

import model.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PropertyReader {
    private static ResourceBundle resourceBundle;

    public static String getType() {
        resourceBundle = ResourceBundle.getBundle("db");
        return resourceBundle.getString("typeDAO");
    }

    public static Configuration getConfig() {
        Configuration configuration = new Configuration();
        resourceBundle = ResourceBundle.getBundle("hibernate");
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", resourceBundle.getString("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", resourceBundle.getString("hibernate.connection.driver_class"));
        configuration.setProperty("hibernate.connection.url", resourceBundle.getString("hibernate.connection.url"));
        configuration.setProperty("hibernate.connection.username", resourceBundle.getString("hibernate.connection.username"));
        configuration.setProperty("hibernate.connection.password", resourceBundle.getString("hibernate.connection.password"));
        configuration.setProperty("hibernate.show_sql", resourceBundle.getString("hibernate.show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", resourceBundle.getString("hibernate.hbm2ddl.auto")); //update, create
        return configuration;
    }

    public static Connection getConnect() {
        resourceBundle = ResourceBundle.getBundle("jdbc");
        try {
            DriverManager.registerDriver((Driver) Class.forName(resourceBundle.getString("driver")).newInstance());

            StringBuilder url = new StringBuilder();
            url.
                    append(resourceBundle.getString("db.type")).
                    append(resourceBundle.getString("host.name")).
                    append(resourceBundle.getString("port")).
                    append(resourceBundle.getString("db.name") + "?serverTimezone=UTC&").
                    append("user=" + resourceBundle.getString("user") + "&").
                    append("password=" + resourceBundle.getString("password"));

            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Ошибка при получении Connection.");
            throw new IllegalStateException();
        }
    }
}