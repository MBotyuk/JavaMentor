package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService<User> {

    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDaoFactory().getDAO();
    }

    public void createTable() {
        userDAO.createTable();
    }

    public void clearTable() {
        userDAO.clearTable();
    }

    public User getUser(long id) {
        if (getNumberOfUserInTable() > 0) {
            return (User) userDAO.getUser(id);
        }
        return null;
    }

    public boolean editUser(User user) {
        userDAO.editUser(user);
        return true;
    }

    public long getNumberOfUserInTable() {
        return userDAO.getNumberOfUserInTable();
    }

    public long isUser(User user) {
        return userDAO.isUserByEmail(user);
    }

    public List<User> getAllUser() {
        createTable();
        return userDAO.getAllUser();
    }

    public boolean addUser(User user) {
        if (getNumberOfUserInTable() > 0) {

            if (isUser(user) == 0) {

                if (userDAO.addUser(user)) {
                    return true;
                }
                return false;
            }
            return false;

        } else {

            if (userDAO.addUser(user)) {
                return true;
            }
            return false;
        }
    }

    public void delUser(Long id) {
        userDAO.delUser(id);
    }

    public User getUserByEmailPassword(String email, String password) {
        if (getNumberOfUserInTable() > 0) {
            return (User) userDAO.getUserByEmailPassword(email, password);
        }
        return null;
    }

    public User.ROLE getRoleByEmailPassword(String email, String password) {
        User user = getUserByEmailPassword(email, password);

        if (user != null) {
            return user.getRole();
        }
        return User.ROLE.UNKNOWN;
    }
}