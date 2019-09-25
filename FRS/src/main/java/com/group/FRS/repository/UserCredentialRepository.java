package com.group.FRS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.group.FRS.model.UserCredential;


@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
	UserCredential findByUsername(String username);
}