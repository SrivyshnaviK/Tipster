package com.zemoso.tipster.repository;

import com.zemoso.tipster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
