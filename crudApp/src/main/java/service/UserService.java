package service;

import dao.UserDaoFactory;
import model.User;

import java.util.List;

public class UserService {

    UserDaoFactory dao = new UserDaoFactory();

    public UserService() {
    }

    public void createTable() {
        dao.getDAO().createTable();
    }

    public void clearTable() {
        dao.getDAO().clearTable();
    }

    public User getUser(long id) {
        if (dao.getDAO().getNumberOfUserInTable() > 0) {
            return (User) dao.getDAO().getUser(id);
        }
        return null;
    }

    public boolean editUser(User user) {
        if (dao.getDAO().isUser(user) == 0) {
            dao.getDAO().editUser(user);
            return true;
        }
        return false;
    }

    public List<User> getAllUser() {
        createTable();
        return dao.getDAO().getAllUser();
    }

    public boolean addUser(User user) {
        if (dao.getDAO().getNumberOfUserInTable() > 0) {
            if (dao.getDAO().isUser(user) == 0) {
                if (dao.getDAO().addUser(user)) {
                    return true;
                }
                return false;
            }
            return false;

        } else {
            if (dao.getDAO().addUser(user)) {
                return true;
            }
            return false;
        }
    }

    public void delUser(Long id) {
        dao.getDAO().delUser(id);
    }
}
