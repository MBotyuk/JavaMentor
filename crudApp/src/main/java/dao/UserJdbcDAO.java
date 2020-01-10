package dao;

import executor.Executor;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserJdbcDAO implements UserDAO<User>{

    private Connection connection;
    private Executor executor;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
        executor = new Executor(connection);
    }

    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT * FROM users");
            while (result.next()) {
                list.add(new User(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4), result.getString(4)));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Вывести список всех пользователей не удалось.");
            return null;
        }
    }

    public User getUser(long id) {
        if (getNumberOfUserInTable() > 0) {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("select * from users where id= " + id + "");
                ResultSet result = stmt.getResultSet();
                while (result.next()) {
                    Long idUser = result.getLong(1);
                    String firstName = result.getString(2);
                    String secondName = result.getString(3);
                    String email = result.getString(4);
                    String password = result.getString(5);
                    result.close();
                    return new User(idUser, firstName, secondName, email, password);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Найти пользователя по id не удалось.");
            }
        }
        return null;
    }

    public void delUser(long id) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users WHERE id = '" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Удалить пользователя не удалось.");
        }
    }

    public boolean addUser(User user) {

        boolean result = false;

        try {
            connection.setAutoCommit(false);
            createTable();

            if (isUser(user) == 0) {
                executor.execUpdate("INSERT INTO users (firstName, secondName, email, password) VALUES ('"
                        + user.getFirstName() + "', '" + user.getSecondName() + "', '"
                        + user.getEmail() + "', '" + user.getPassword() + "')");
                result = true;
            }
            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.err.println("Добавить пользователя не удалось.");
            return false;
        } finally {

            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public long isUser(User user) {
        if (getNumberOfUserInTable() > 0) {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("select * from users where email='" + user.getEmail() + "' and password='" + user.getPassword() + "'");
                ResultSet result = stmt.getResultSet();
                while (result.next()) {
                    Long id = result.getLong(1);
                    result.close();
                    return id;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Проверить пользователя не удалось.");
            }
        }
        return 0;
    }

    public long getNumberOfUserInTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeQuery("SELECT EXISTS (SELECT 1 FROM users)");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                long number = result.getLong(1);
                result.close();
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось посчитать количество записей в таблице.");
        }
        return 0;
    }

    public void editUser(User user) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("UPDATE users SET firstName = '" + user.getFirstName() + "', secondName= '" +
                    user.getSecondName() + "', email= '" + user.getEmail() + "', password= '" + user.getPassword() +
                    "' WHERE id = " + user.getId() + "");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось отредактировать пользователя.");
        }
    }

    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("create table if not exists users (id bigint auto_increment, firstName varchar(256), secondName varchar(256), email varchar(256), password varchar(256), primary key (id))");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось создать таблицу.");
        }
    }

    public void clearTable() {
        try (Statement stmt = connection.createStatement()) {
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось очистить таблицу.");
        }
    }
}
