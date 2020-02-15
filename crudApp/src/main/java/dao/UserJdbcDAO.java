package dao;

import executor.Executor;
import executor.ResultHandler;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserJdbcDAO implements UserDAO<User> {

    private Connection connection;
    private Executor executor;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
        executor = new Executor(connection);
        createTable();
    }

    @Override
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        try {
            list = executor.execQuery("SELECT * FROM users", new ResultHandler<List>() {
                @Override
                public List handle(ResultSet resultSet) throws SQLException {
                    List<User> listResult = new ArrayList<>();
                    while (resultSet.next()) {
                        listResult.add(new User(resultSet.getInt("id"), resultSet.getString("firstName"),
                                resultSet.getString("secondName"), resultSet.getString("email"),
                                resultSet.getString("password"), User.ROLE.valueOf(resultSet.getString("role"))));
                    }
                    return listResult;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Вывести список всех пользователей не удалось.");
        }
        return list;
    }

    @Override
    public User getUser(long id) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("select * from users where id= " + id + "");
            ResultSet result = stmt.getResultSet();

            while (result.next()) {
                Long idUser = result.getLong("id");
                String firstName = result.getString("firstName");
                String secondName = result.getString("secondName");
                String email = result.getString("email");
                String password = result.getString("password");
                String role = result.getString("role");
                result.close();
                return new User(idUser, firstName, secondName, email, password, User.ROLE.valueOf(role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Найти пользователя по id не удалось.");
        }
        return null;
    }

    @Override
    public void delUser(long id) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users WHERE id = '" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Удалить пользователя не удалось.");
        }
    }

    @Override
    public boolean addUser(User user) {
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            executor.execUpdate("INSERT INTO users (firstName, secondName, email, password, role) VALUES ('"
                    + user.getFirstName() + "', '" + user.getSecondName() + "', '"
                    + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getRole() + "')");
            result = true;

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

    @Override
    public long isUserByEmail(User user) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("select * from users where email='" + user.getEmail() + "'");
            ResultSet result = stmt.getResultSet();

            while (result.next()) {
                Long id = result.getLong("id");
                result.close();
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Проверить пользователя не удалось.");
        }

        return 0;
    }

    @Override
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

    @Override
    public boolean editUser(User user) {
        boolean result = false;
        try (Statement stmt = connection.createStatement()) {
            connection.setAutoCommit(false);
            stmt.executeUpdate("UPDATE users SET firstName = '" + user.getFirstName() + "', secondName= '" +
                    user.getSecondName() + "', email= '" + user.getEmail() + "', password= '" + user.getPassword() +
                    "', role= '" + user.getRole() + "' WHERE id = " + user.getId() + "");
            result = true;
            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.err.println("Не удалось отредактировать пользователя.");
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

    @Override
    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("create table if not exists users (id bigint auto_increment, firstName varchar(256), secondName varchar(256), email varchar(256), password varchar(256), role types.varchar(5), primary key (id))");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось создать таблицу.");
        }
    }

    @Override
    public void clearTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось очистить таблицу.");
        }
    }

    @Override
    public User getUserByEmailPassword(String email, String password) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("select * from users where email= '" + email + "' and password= '" + password + "'");
            ResultSet result = stmt.getResultSet();

            while (result.next()) {
                Long idUser = result.getLong("id");
                result.close();
                return getUser(idUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Найти пользователя по id не удалось.");
        }
        return null;
    }
}