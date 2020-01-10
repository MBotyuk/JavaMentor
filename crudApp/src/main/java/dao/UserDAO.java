package dao;

import java.util.List;

public interface UserDAO<T> {

    List<T> getAllUser();

    T getUser(long id);

    boolean addUser(T t);

    void delUser(long id);

    void editUser(T t);

    long getNumberOfUserInTable();

    long isUser(T t);

    void createTable();

    void clearTable();
}
