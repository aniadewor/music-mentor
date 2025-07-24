package com.musicmentor.musicmentor.repository;

import com.musicmentor.musicmentor.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer>{
}
