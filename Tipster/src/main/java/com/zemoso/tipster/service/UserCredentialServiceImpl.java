package com.zemoso.tipster.service;

import com.zemoso.tipster.entity.UserCredential;
import com.zemoso.tipster.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserCredentialServiceImpl implements UserCredentialService{
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    public UserCredentialServiceImpl(UserCredentialRepository userCredentialRepository){
        this.userCredentialRepository=userCredentialRepository;
    }
    @Override
    @Transactional
    public List<UserCredential> findAll() {
        return userCredentialRepository.findAll();
    }

    @Override
    @Transactional
    public UserCredential findById(int id) {
        Optional<UserCredential> userCredential=userCredentialRepository.findById(id);
        if(userCredential.isPresent())
            return userCredential.get();
        throw new RuntimeException("UserCredentials Not Found:"+id);
    }

    @Override
    @Transactional
    public void save(UserCredential userCredential) {
        userCredentialRepository.save(userCredential);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        userCredentialRepository.deleteById(id);
    }
}
