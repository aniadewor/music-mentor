package com.musicmentor.musicmentor.repository;

import com.musicmentor.musicmentor.model.Quiz;
import com.musicmentor.musicmentor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  QuizRepository extends JpaRepository<Quiz,Integer> {
    List<Quiz> findByOwner(User owner);
}
