package com.musicmentor.musicmentor.controller;

import com.musicmentor.musicmentor.model.Lesson;
import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.request.AddLessonRequest;
import com.musicmentor.musicmentor.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/lessons")
@CrossOrigin(origins = "http://localhost:4200")
public class LessonController {
    private LessonService lessonService;

    //    @PostMapping("/createLesson")
//    public ResponseEntity<Lesson> createLesson(@RequestBody AddLessonRequest lesson, @RequestParam Integer userId, @RequestParam MultipartFile file) throws IOException {
//        Lesson savedLesson = lessonService.createLesson(lesson, userId, file);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedLesson);
//    }

    @PostMapping(
            value = "/createLesson",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Lesson> createLesson(
            @RequestPart("lesson") AddLessonRequest lesson,
            @RequestParam Integer userId,
            @RequestPart(value = "file", required = false) MultipartFile file // jeśli plik bywa opcjonalny
    ) throws IOException {
        Lesson savedLesson = lessonService.createLesson(lesson, userId, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLesson);
    }
    @GetMapping("/getLessons")
    public ResponseEntity<List<Lesson>> getLessons(@RequestParam String filter, @RequestParam Role role){
        List<Lesson> lessons = lessonService.getLessons(filter, role);
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/getLessonsById")
    public ResponseEntity<Lesson> getLessonById(@RequestParam Integer lessonId){
        Lesson lesson = lessonService.getLessonById(lessonId);
        return ResponseEntity.ok(lesson);
    }
}
