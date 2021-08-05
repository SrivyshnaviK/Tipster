package com.zemoso.tipster.service;

import com.zemoso.tipster.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int id);
    void save(User user);
    void deleteById(int id);
    Page<User> findPaginated(int pageNo,int pageSize);
}
