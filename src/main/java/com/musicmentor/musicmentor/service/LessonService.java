package com.musicmentor.musicmentor.service;

import com.musicmentor.musicmentor.model.Lesson;
import com.musicmentor.musicmentor.model.User;
import com.musicmentor.musicmentor.repository.LessonRepository;
import com.musicmentor.musicmentor.repository.UserRepository;
import com.musicmentor.musicmentor.request.AddLessonRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class LessonService {
    private LessonRepository lessonRepository;
    private UserRepository userRepository;

    public Lesson createLesson(AddLessonRequest lesson, Integer userId) {
        Lesson newLesson = new Lesson();
        userRepository.findById(userId).ifPresent(user -> {
            newLesson.setAuthor(user.getName() + " " + user.getLastName());
        });
          newLesson.setCreationDate(LocalDateTime.now());
          newLesson.setTitle(lesson.getTitle());
          newLesson.setDescription(lesson.getDescription());
          newLesson.setContent(lesson.getContent());
          newLesson.setClassName(lesson.getClassName());
          lessonRepository.save(newLesson);
          return newLesson;
    }
    public List<Lesson> getLessons(String className) {
       return lessonRepository.findByClassName(className);
    }
}
