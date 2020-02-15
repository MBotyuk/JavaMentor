package com.gmail.mbotyuk.dao;

import com.gmail.mbotyuk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    public final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("Select u from User u", User.class).getResultList();
    }

    @Override
    public boolean add(User user) {
        try {
            entityManager.persist(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public boolean isByEmail(String email) {
        List results = entityManager.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email).getResultList();
        if (results.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public User isByName(String name) {
        try {
            Query query = entityManager.createNamedQuery("User.findByName", User.class);
            query.setParameter("name", name);
            User result2 = (User) query.getSingleResult();
            return result2;
        } catch (NoResultException nre) {
            return null;
        }
    }
}