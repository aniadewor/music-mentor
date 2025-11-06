package com.musicmentor.musicmentor.service;

import com.musicmentor.musicmentor.model.Lesson;
import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.model.User;
import com.musicmentor.musicmentor.repository.LessonRepository;
import com.musicmentor.musicmentor.repository.UserRepository;
import com.musicmentor.musicmentor.request.AddLessonRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class LessonService {
    private LessonRepository lessonRepository;
    private UserRepository userRepository;

    public Lesson createLesson(AddLessonRequest lesson, Integer userId, MultipartFile file) throws IOException {
        Lesson newLesson = new Lesson();
        userRepository.findById(userId).ifPresent(user -> {
            newLesson.setAuthor(user.getName() + " " + user.getLastName());
        });
          newLesson.setCreationDate(LocalDateTime.now());
          newLesson.setTitle(lesson.getTitle());
          newLesson.setDescription(lesson.getDescription());
          newLesson.setContent(lesson.getContent());
          newLesson.setClassName(lesson.getClassName());
          newLesson.setImageName(file.getOriginalFilename());
          newLesson.setImage(file.getBytes());
          lessonRepository.save(newLesson);
          return newLesson;
    }
    public List<Lesson> getLessons(String filter, Role role) {
        List <Lesson> lessons = new ArrayList<>();
        if (role == Role.STUDENT){
             lessons = lessonRepository.findByClassName(filter);}
        else if (role == Role.TEACHER) {
             lessons = lessonRepository.findByAuthor(filter);
        }
        return lessons;
    }

    public Lesson getLessonById(Integer lessonId) {
         return lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Lesson not found"));
    }
}
