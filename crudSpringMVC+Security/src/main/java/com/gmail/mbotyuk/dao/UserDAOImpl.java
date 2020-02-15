package com.gmail.mbotyuk.dao;

import com.gmail.mbotyuk.model.Role;
import com.gmail.mbotyuk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Optional<User> isByName(String name) {
        return Optional.ofNullable(entityManager.createNamedQuery("User.findByName", User.class)
                .setParameter("name", name).getSingleResult());
    }

    @Override
    public boolean add(User user) {
        try {
//            user.setRoles(Collections.singleton("User"));
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            entityManager.persist(user);
            //userRepository.save(user);
            return true;
        } catch (Exception e){
            return false;
        }
    }


}