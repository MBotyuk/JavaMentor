package com.gmail.mbotyuk.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Component
@Entity
@Table(name = "roles"
        //, uniqueConstraints = @UniqueConstraint(columnNames = {"role", "username"})
        )
public class Role implements GrantedAuthority {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", nullable = false)
    private Long id;

    //@Column(name = "name", nullable = true)
    private String name;

    //@Transient
//    @ManyToMany(
//            //mappedBy = "roles"
//            )

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}