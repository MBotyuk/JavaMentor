package com.gmail.mbotyuk.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findByEmail",
                query = "SELECT u FROM User u WHERE u.email = :email"),
        @NamedQuery(name = "User.findByName",
                query = "SELECT u FROM User u WHERE u.name = :name")})
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @NaturalId
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;

//    @ManyToMany(fetch = FetchType.EAGER
//            //, mappedBy = "users"
//            )
   // @JoinColumn(name= "roles")

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "user_role",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
