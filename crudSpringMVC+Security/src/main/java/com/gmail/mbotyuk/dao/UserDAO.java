package com.gmail.mbotyuk.dao;

import com.gmail.mbotyuk.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserDAO {



    boolean add(User user);


     Optional<User> isByName(String name);


}
