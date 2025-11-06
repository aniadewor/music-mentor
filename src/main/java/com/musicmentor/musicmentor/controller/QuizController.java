package com.musicmentor.musicmentor.controller;

import com.musicmentor.musicmentor.model.*;
import com.musicmentor.musicmentor.repository.UserRepository;
import com.musicmentor.musicmentor.request.AddQuestionRequest;
import com.musicmentor.musicmentor.request.AddQuizRequest;
import com.musicmentor.musicmentor.response.QuizResponse;
import com.musicmentor.musicmentor.response.TeacherQuizResponse;
import com.musicmentor.musicmentor.response.UserQuizResponse;
import com.musicmentor.musicmentor.service.QuizService;
import com.musicmentor.musicmentor.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/quizzes")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {
    @Autowired
    private QuizService quizService;

    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/addQuiz")
    public ResponseEntity<AddQuizRequest> addQuiz(@RequestBody AddQuizRequest quiz) {
        quizService.createQuiz(quiz);
        return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
    }

    @PostMapping("/addQuestions")
    public ResponseEntity<AddQuestionRequest> addQuestions(@RequestBody AddQuestionRequest addQuestionRequest) {
        quizService.addQuestions(addQuestionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(addQuestionRequest);
    }

    @GetMapping ("/getQuizById")
    public ResponseEntity<QuizResponse> getQuizById(@RequestParam Integer id) {
        QuizResponse quiz = quizService.getQuizById(id);
        return ResponseEntity.status(HttpStatus.OK).body(quiz);
    }
    @PostMapping ("/updateQuizScore")
    public ResponseEntity <?> updateQuizScore(@RequestParam Integer quizId) {
        int scoreSum = quizService.updateQuizScore(quizId);
        return ResponseEntity.status(HttpStatus.OK).body(scoreSum);
    }
    @GetMapping("/getUserQuizDate")
    public ResponseEntity <List<UserQuizResponse>> getUserQuizDate (@RequestParam Integer userId) {
        List<UserQuizResponse> userQuizResponses = quizService.getUserQuizDate(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userQuizResponses);
    }
    @GetMapping("/getTeacherQuizDate")
    public ResponseEntity<List<TeacherQuizResponse>> getTeacherQuizDate (@RequestParam String className){
        List<TeacherQuizResponse> teacherQuizResponses = quizService.getTeacherQuizDate(className);
        return ResponseEntity.status(HttpStatus.OK).body(teacherQuizResponses);
    }
    @GetMapping ("/getQuizzesByUserId")
    public List<Quiz> getQuizzesByUserId(@RequestParam Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return quizService.getQuizzesByUserId(user);
    }
    @PostMapping("/updateClassName")
    public ResponseEntity<Quiz> updateClassName(@RequestParam Integer quizId, @RequestParam List<String> className) {
        Quiz quiz = quizService.updateClassName(quizId, className);
        return ResponseEntity.status(HttpStatus.OK).body(quiz);
    }
    @PostMapping ("/getQuizzesByClass")
    public ResponseEntity <List<Quiz>> getQuizzesByClass(@RequestParam String className, @RequestParam Integer userId) {
        List <Quiz> quizList = quizService.getQuizzesByClass(className, userId);
        return ResponseEntity.status(HttpStatus.OK).body(quizList);
    }
}
