package com.musicmentor.musicmentor.service;

import com.musicmentor.musicmentor.model.Quiz;
import com.musicmentor.musicmentor.repository.QuizRepository;
import com.musicmentor.musicmentor.request.AddQuizRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class QuizService {
    private final QuizRepository quizRepository;
    public Quiz addQuiz(AddQuizRequest addQuizRequest){
        Quiz quiz = new Quiz();
        quiz.setTitle(addQuizRequest.getTitle());
        quiz.setDescription(addQuizRequest.getDescription());
        quiz.setOwner(addQuizRequest.getOwner());
        return quizRepository.save(quiz);
    }
}
