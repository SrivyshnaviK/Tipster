package com.zemoso.tipster.repository;

import com.zemoso.tipster.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential,Integer> {
}
