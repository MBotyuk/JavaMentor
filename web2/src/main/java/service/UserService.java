package service;

import model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {

    /* хранилище данных */
    private Map<Long, User> dataBase = Collections.synchronizedMap(new HashMap<>());
    /* счетчик id */
    private AtomicLong maxId = new AtomicLong(0);
    /* список авторизованных пользователей */
    private Map<Long, User> authMap = Collections.synchronizedMap(new HashMap<>());

    private static UserService userService; // для реализации Singleton

    private UserService(){} // приватный конструктор чтобы запретить создавать через new

    public static UserService getUserService() { // метод для получения нового объекта UserService если ранее не создан
        if (userService == null) {        //если объект еще не создан
            userService = new UserService();    //создать новый объект
        }
        return userService;        // вернуть ранее созданный объект
    }

    public List<User> getAllUsers() {
        return (List<User>) dataBase.values();
    }

    public User getUserById(Long id) {
        return dataBase.get(id);
    }

    public boolean addUser(User user) {
        boolean result = isExistsThisUser(user);
        if(!dataBase.containsKey(user.getId())){
            dataBase.put(maxId.incrementAndGet(), user);
            return true;
        }
        return false;
    }

    public void deleteAllUser() {
        dataBase.clear();
    }

    public boolean isExistsThisUser(User user) {
        return dataBase.containsValue(user);
    }

    public List<User> getAllAuth() {
        return (List<User>) authMap.values();
    }

    public boolean authUser(User user) {
        return authMap.containsValue(user);
    }

    public void logoutAllUsers() {
        authMap.clear();
    }

    public boolean isUserAuthById(Long id) {

        return false;
    }

}
