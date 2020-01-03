package dao;

import executor.Executor;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {

    private Connection connection;
    private Executor executor;

    public UserDAO(Connection connection) {
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
            return null;
        }
    }

    public boolean addUser(User user) {
        if (isUser(user) == 0) {
            executor.execUpdate("INSERT INTO users (firstName, secondName, email, password) VALUES ('"
                    + user.getFirstName() + "', '" + user.getSecondName() + "', '"
                    + user.getEmail() + "', '" + user.getPassword() + "')");
            return true;
        }
        return false;
    }

    public long getNumberOfUserInTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeQuery("SELECT EXISTS (SELECT 1 FROM users)");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                Long number = result.getLong(1);
                result.close();
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
            }
        }
        return 0;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, firstName varchar(256), secondName varchar(256), email varchar(256), password varchar(256), primary key (id))");
        stmt.close();
    }

    public void clearTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }

    public void delUser(Long id) throws SQLException {
        try (Statement stmt = connection.createStatement();) {
            stmt.execute("DELETE FROM users WHERE id = '" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
