package com.group.FRS.model;

import java.util.Set;

import javax.persistence.*;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_credential")
public class UserCredential {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	@Column(name="type")
	private String type;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="login_status")
	private boolean loginStatus;
	
	
	
	
	@Transient
	private String passwordConfirm;
	
//	@ManyToMany(mappedBy="userCredentials")
//	private Set<Role> roles;

	
	@ManyToMany
	@JoinTable(
	  name = "user_credential_roles", 
	  joinColumns = @JoinColumn(name = "user_credential_id"), 
	  inverseJoinColumns = @JoinColumn(name = "role_id"))
	Set<Role> roles;
	
	
	
	
	@OneToOne(mappedBy = "userCredential")
	@JsonIgnore
    private UserProfile userProfile;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
