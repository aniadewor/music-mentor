package com.musicmentor.musicmentor.controller;

import com.musicmentor.musicmentor.model.Quiz;
import com.musicmentor.musicmentor.model.User;
import com.musicmentor.musicmentor.request.AddQuizRequest;
import com.musicmentor.musicmentor.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/quizzes")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/addQuiz")
    public ResponseEntity<AddQuizRequest> addQuiz(@RequestBody AddQuizRequest quiz) {
        System.out.println("Received user: " + quiz);
        quizService.addQuiz(quiz);
        return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
    }
}