package service;

import java.util.List;

public interface UserService<T> {

    void createTable();

    void clearTable();

    T getUser(long id);

    boolean editUser(T t);

    public long getNumberOfUserInTable();

    long isUser(T t);

    List<T> getAllUser();

    void delUser(Long id);

    boolean addUser(T t);

    T getUserByEmailPassword(String email, String password);

    Enum getRoleByEmailPassword(String email, String password);
}