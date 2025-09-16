package com.musicmentor.musicmentor.controller;

import com.musicmentor.musicmentor.model.Answer;
import com.musicmentor.musicmentor.response.AnswerResponse;
import com.musicmentor.musicmentor.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/answers")
@CrossOrigin(origins = "http://localhost:4200")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping("/saveQuizAnswer")
    public ResponseEntity<Answer> saveQuizAnswer(@RequestParam Integer quizId, @RequestParam Integer userId, @RequestParam List<Integer> questionIdList, @RequestParam List <String> studentAnswerList) {
        System.out.println("Received user: " + userId);
        Answer answer = answerService.saveQuizAnswer(quizId, userId,questionIdList, studentAnswerList);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }
    @PostMapping("/checkQuizCorrectAnswer")
    public ResponseEntity <AnswerResponse> checkQuizCorrectAnswer (@RequestParam Integer userId, @RequestParam Integer quizId) {
        AnswerResponse answerResponse = answerService.checkQuizCorrectAnswer(userId, quizId);
        return ResponseEntity.status(HttpStatus.OK).body(answerResponse);
    }
}
