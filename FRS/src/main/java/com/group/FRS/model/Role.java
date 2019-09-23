package com.group.FRS.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
//    @JoinTable(
//    		name="user_credential_roles", 
//    		joinColumns = @JoinColumn(name="role_id"), 
//    		inverseJoinColumns = @JoinColumn(name="user_credential_id"))
//    private Set<UserCredential> userCredentials;

    @ManyToMany(mappedBy = "roles")
    Set<UserCredential> userCredentials;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserCredential> getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(Set<UserCredential> userCredentials) {
        this.userCredentials = userCredentials;
    }
}
