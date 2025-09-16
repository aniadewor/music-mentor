package com.musicmentor.musicmentor.repository;

import com.musicmentor.musicmentor.model.Lesson;
import com.musicmentor.musicmentor.model.Question;
import com.musicmentor.musicmentor.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer>{
    List<Lesson> findByClassName(String className);
}
