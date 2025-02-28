package com.musicmentor.musicmentor.service;

import com.musicmentor.musicmentor.model.Quiz;
import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.model.User;
import com.musicmentor.musicmentor.repository.QuizRepository;
import com.musicmentor.musicmentor.request.AddQuizRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private UserService userService;
    public Quiz addQuiz(AddQuizRequest addQuizRequest){
        Quiz quiz = new Quiz();
        User user = new User();
        Optional<User> owner = Optional.empty();
        quiz.setTitle(addQuizRequest.getTitle());
        quiz.setDescription(addQuizRequest.getDescription());
        user.setId(addQuizRequest.ownerId);
        quiz.setOwner(user);
        owner = userService.getUserById(addQuizRequest.ownerId);
        if(owner.get().getRole() == Role.TEACHER){
            return quizRepository.save(quiz);
        }
        else {
            throw new IllegalArgumentException("Invalid role");
        }
    }
}
