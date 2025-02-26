package com.musicmentor.musicmentor.repository;

import com.musicmentor.musicmentor.model.Quiz;
import com.musicmentor.musicmentor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  QuizRepository extends JpaRepository<Quiz,Integer> {
}
