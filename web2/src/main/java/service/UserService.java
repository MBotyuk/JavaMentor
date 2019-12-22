package service;

import model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {

    /* хранилище данных */
    private Map<Long, User> dataBase = Collections.synchronizedMap(new HashMap<>());
    /* счетчик id для dataBase */
    private AtomicLong maxId = new AtomicLong(0);
    /* список авторизованных пользователей */
    private Map<Long, User> authMap = Collections.synchronizedMap(new HashMap<>());

    // для реализации Singleton
    private static UserService userService;

    // приватный конструктор чтобы запретить создавать через new
    private UserService() {
    }

    public static UserService getUserService() { // метод для получения нового объекта UserService если ранее не создан
        if (userService == null) {        //если объект еще не создан
            userService = new UserService();    //создать новый объект
        }
        return userService;        // вернуть ранее созданный объект
    }

    public List<User> getAllUsers() {
        ArrayList<User> usersDataBase = new ArrayList<>();
        for (long i = 1; i <= maxId.longValue(); i++) {
            usersDataBase.add(getUserById(i));
        }
        return usersDataBase;
    }

    public User getUserById(Long id) {
        return dataBase.get(id);
    }

    public boolean addUser(User user) {
        if (dataBase.isEmpty()) {
            user.setId(maxId.incrementAndGet());
            dataBase.put(user.getId(), user);
            return true;
        }

        if (!isExistsThisUser(user)) {
            user.setId(maxId.incrementAndGet());
            dataBase.put(user.getId(), user);
            return true;
        }
        return false;
    }

    public void deleteAllUser() {
        dataBase.clear();
        authMap.clear();
        maxId = new AtomicLong(0);
    }

    public boolean isExistsThisUser(User user) {
        for (User userDataBase : getAllUsers()) {
            if (user.equals(userDataBase)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getAllAuth() {
        Collection<User> list = authMap.values(); // код взял с https://docs.oracle.com/ SynchronizedMap
        ArrayList<User> usersDataBase = new ArrayList<>();

        synchronized (list) { // мне кажется смысла в этом блоке нет
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                usersDataBase.add((User) iterator.next());
            }
        }
        return usersDataBase;
    }

    public boolean authUser(User user) {
        if (isExistsThisUser(user) & !isUserAuthByUser(user)) {
            for (User userDataBase : getAllUsers()) {
                if (user.getPassword().equals(userDataBase.getPassword())) {
                    authMap.put(userDataBase.getId(), userDataBase);
                    return true;
                }
            }
        }
        return false;
    }

    public void logoutAllUsers() {
        authMap.clear();
    }

    public boolean isUserAuthById(Long id) {
        for (User userAuth : getAllAuth()) {
            if (userAuth.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserAuthByUser(User user) {
        for (User userAuthMap : getAllAuth()) {
            if (user.equals(userAuthMap)) {
                return true;
            }
        }
        return false;
    }
}
