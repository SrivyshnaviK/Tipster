package com.zemoso.tipster.service;

import com.zemoso.tipster.entity.UserCredential;

import java.util.List;

public interface UserCredentialService {
    List<UserCredential> findAll();

    UserCredential findById(int id);

    void save(UserCredential userCredential);

    void deleteById(int id);
}
