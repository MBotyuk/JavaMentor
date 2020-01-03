package service;

import dao.UserDAO;
import model.User;

import java.sql.*;
import java.util.List;

public class UserService {

    public UserService() {
    }

    public void createTable() {
        UserDAO dao = getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTable() {
        UserDAO dao = getUserDAO();
        try {
            dao.clearTable();
        } catch (SQLException e) {
        }
    }

    public List<User> getAllUser() {
        UserDAO userDAO = getUserDAO();
        if (userDAO.getAllUser() != null) {
            return userDAO.getAllUser();
        }
        return null;
    }

    public boolean addUser(User user) {
        UserDAO userDAO = getUserDAO();
        boolean result = false;

        try {
            userDAO.getConnection().setAutoCommit(false);
            createTable();
            if (userDAO.addUser(user)) {
                result = true;
            }
            userDAO.getConnection().commit();
        } catch (SQLException e) {

            try {
                userDAO.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {

            try {
                userDAO.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean delUser(Long id) {
        try {
            getUserDAO().delUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static Connection getMysqlConnection() {

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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    } // +

    public static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }
}
