package com.musicmentor.musicmentor.repository;

import com.musicmentor.musicmentor.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findByQuizId (Integer quizId);

    List<Question> findAllByQuizId(Integer quizId);

}

