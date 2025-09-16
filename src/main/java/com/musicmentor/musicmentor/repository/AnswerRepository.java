package com.musicmentor.musicmentor.repository;

import com.musicmentor.musicmentor.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer>{
    List<Answer> findByUserIdAndQuizId(Integer userId, Integer quizId);

}
