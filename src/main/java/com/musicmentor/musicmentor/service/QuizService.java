package com.musicmentor.musicmentor.service;

import com.musicmentor.musicmentor.model.Question;
import com.musicmentor.musicmentor.model.Quiz;
import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.model.User;
import com.musicmentor.musicmentor.repository.QuestionRepository;
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
    private final QuestionRepository questionRepository;
    public Quiz addQuiz(AddQuizRequest addQuizRequest){
        return createQuiz(addQuizRequest);
    }
    private Quiz createQuiz(AddQuizRequest addQuizRequest) {
        Quiz quiz = new Quiz();
        User user = new User();
        Optional<User> owner = Optional.empty();
        quiz.setTitle(addQuizRequest.getTitle());
        quiz.setNumberOfQuestions(addQuizRequest.getNumberOfQuestions());
        quiz.setDescription(addQuizRequest.getDescription());
        user.setId(addQuizRequest.ownerId);
        quiz.setOwner(user);

        addQuestions(addQuizRequest);

        owner = userService.getUserById(addQuizRequest.ownerId);
        if(owner.get().getRole() == Role.TEACHER){
            return quizRepository.save(quiz);
        }
        else {
            throw new IllegalArgumentException("Invalid role");
        }
    }

    private void addQuestions(AddQuizRequest addQuizRequest) {
        for (int i = 0; i < addQuizRequest.getNumberOfQuestions(); i++ ) {
           Question question = addQuizRequest.getQuestionList().get(i);
            question.getQuestionTitle();
            if (question.getQuestionTitle()==null){
                throw new NullPointerException("Question title is null");
            }
            questionRepository.save(addQuizRequest.getQuestionList().get(i));
        }
    }
}
