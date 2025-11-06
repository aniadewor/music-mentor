package com.musicmentor.musicmentor.repository;

import com.musicmentor.musicmentor.model.Answer;
import com.musicmentor.musicmentor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User,Integer> {
    User findByEmail(String email);
    List<User> findByClassName(String className);
}
