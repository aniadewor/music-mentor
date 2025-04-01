package com.musicmentor.musicmentor.service;

import com.musicmentor.musicmentor.model.Question;
import com.musicmentor.musicmentor.model.Quiz;
import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.model.User;
import com.musicmentor.musicmentor.repository.QuestionRepository;
import com.musicmentor.musicmentor.repository.QuizRepository;
import com.musicmentor.musicmentor.request.AddQuestionRequest;
import com.musicmentor.musicmentor.request.AddQuizRequest;
import com.musicmentor.musicmentor.request.QuestionRequest;
import com.musicmentor.musicmentor.response.QuizResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private UserService userService;
    private final QuestionRepository questionRepository;

    public Quiz createQuiz(AddQuizRequest addQuizRequest) {
        Quiz quiz = new Quiz();
        User user = new User();
        Optional<User> owner = Optional.empty();
        quiz.setTitle(addQuizRequest.getTitle());
        quiz.setNumberOfQuestions(addQuizRequest.getNumberOfQuestions());
        quiz.setDescription(addQuizRequest.getDescription());
        user.setId(addQuizRequest.ownerId);
        quiz.setOwner(user);

        owner = userService.getUserById(addQuizRequest.ownerId);
        if (owner.get().getRole() == Role.TEACHER) {
            quizRepository.save(quiz);
        } else {
            throw new IllegalArgumentException("Invalid role");
        }

        addQuizRequest.setQuizId(quiz.getId());

        return quiz;
    }

    public void addQuestions(AddQuestionRequest addQuestionRequest) {
        Quiz quiz = quizRepository.findById(addQuestionRequest.getQuizId()).orElseThrow(() -> new RuntimeException("Quiz not found"));

        for (QuestionRequest question : addQuestionRequest.getQuestionList()) {
            Question questionToAdd = new Question();
            questionToAdd.setQuiz(quiz);
            questionToAdd.setAnswer1(question.getAnswer1());
            questionToAdd.setAnswer2(question.getAnswer2());
            questionToAdd.setAnswer3(question.getAnswer3());
            questionToAdd.setAnswer4(question.getAnswer4());
            questionToAdd.setScore(question.getScore());
            questionToAdd.setCorrectAnswer(question.getCorrectAnswer());
            questionToAdd.setType(question.getType());
            questionToAdd.setQuestionTitle(question.getQuestionTitle());

            questionRepository.save(questionToAdd);
        }
    }

    public QuizResponse getQuizById(Integer quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<Question> question = questionRepository.findAllByQuizId(quizId);
        QuizResponse quizResponse = new QuizResponse();
        quizResponse.setId(quiz.getId());
        quizResponse.setTitle(quiz.getTitle());
        quizResponse.setScore(quiz.getScore());
        quizResponse.setDescription(quiz.getDescription());
        quizResponse.setQuestionList(question);
        return quizResponse;
    }
}
