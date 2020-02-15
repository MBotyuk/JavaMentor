package dao;

import java.util.List;

public interface UserDAO<T> {

    List<T> getAllUser();

    T getUser(long id);

    boolean addUser(T t);

    void delUser(long id);

    boolean editUser(T t);

    long getNumberOfUserInTable();

    long isUserByEmail(T t);

    void createTable();

    void clearTable();

    T getUserByEmailPassword(String email, String password);
}