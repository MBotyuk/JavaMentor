package com.gmail.mbotyuk.springbootmvcdata.repositories;

import com.gmail.mbotyuk.springbootmvcdata.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    List<User> findAll();

    <S extends User> S save(S s);

    Optional<User> findById(Long aLong);

    void deleteById(Long aLong);

    //<S extends User> Optional<S> findOne(Example<S> example);

    <S extends User> S saveAndFlush(S s);

    User findByName(String name);

    User findByEmail(String email);

//    @Query("select u from User u where u.name = :name")
//    User findByName(@Param("name") String name);
//
//    @Query("select u from User u where u.email = :email")
//    User findByEmail(@Param("email") String email);

    //User findByUsername(String username);

    //Optional<User> findOneByLogin(String login);
}
