package com.musicmentor.musicmentor.controller;

import com.musicmentor.musicmentor.model.Lesson;
import com.musicmentor.musicmentor.request.AddLessonRequest;
import com.musicmentor.musicmentor.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/lessons")
@CrossOrigin(origins = "http://localhost:4200")
public class LessonController {
    private LessonService lessonService;

    @PostMapping("/createLesson")
    public ResponseEntity<Lesson> createLesson(AddLessonRequest lesson, Integer userId){
        Lesson savedLesson = lessonService.createLesson(lesson, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLesson);
    }
    @GetMapping("/getLessons")
    public ResponseEntity<List<Lesson>> getLessons(String className){
        List<Lesson> lessons = lessonService.getLessons(className);
        return ResponseEntity.ok(lessons);
    }
}
