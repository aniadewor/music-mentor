package com.musicmentor.musicmentor.service;

import com.musicmentor.musicmentor.model.*;
import com.musicmentor.musicmentor.repository.AnswerRepository;
import com.musicmentor.musicmentor.repository.QuestionRepository;
import com.musicmentor.musicmentor.repository.QuizRepository;
import com.musicmentor.musicmentor.request.AddQuestionRequest;
import com.musicmentor.musicmentor.request.AddQuizRequest;
import com.musicmentor.musicmentor.request.QuestionRequest;
import com.musicmentor.musicmentor.response.QuizResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private UserService userService;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public Quiz createQuiz(AddQuizRequest addQuizRequest) {
        Quiz quiz = new Quiz();
        User user = new User();
        Optional<User> owner = Optional.empty();
        quiz.setTitle(addQuizRequest.getTitle());
        quiz.setNumberOfQuestions(addQuizRequest.getNumberOfQuestions());
        quiz.setDescription(addQuizRequest.getDescription());
        user.setId(addQuizRequest.ownerId);
        quiz.setOwner(user);
        quiz.setSchoolName(addQuizRequest.getSchoolName());

        owner = userService.getUserById(addQuizRequest.ownerId);
        if (owner.get().getRole() == Role.TEACHER) {
            quizRepository.save(quiz);
        } else {
            throw new IllegalArgumentException("Invalid role");
        }

        addQuizRequest.setQuizId(quiz.getId());

        return quiz;
    }

    public int addQuestions(AddQuestionRequest addQuestionRequest) {
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
        return updateQuizScore(addQuestionRequest.getQuizId());
    }

    public QuizResponse getQuizById(Integer quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<Question> question = questionRepository.findAllByQuizId(quizId);
        QuizResponse quizResponse = new QuizResponse();
        quizResponse.setId(quiz.getId());
        quizResponse.setTitle(quiz.getTitle());
        quizResponse.setScore(quiz.getScoreSum());
        quizResponse.setDescription(quiz.getDescription());
        quizResponse.setQuestionList(question);
        return quizResponse;
    }
    public int updateQuizScore(Integer quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<Question> question = questionRepository.findAllByQuizId(quizId);
        int scoreSum =0;
        for (Question question1 : question) {
           scoreSum += question1.getScore();
        }
        quiz.setScoreSum(scoreSum);
        quizRepository.save(quiz);
        return scoreSum;
    }
    public List<Quiz> getQuizzesByUserId(User owner) {
       List<Quiz> quiz = quizRepository.findByOwner(owner);
        return quiz;
    }
    public Quiz updateClassName(Integer quizId, List<String> className) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        quiz.setClassName(className);
        quizRepository.save(quiz);
        return quiz;
    }
    public List<Quiz> getQuizzesByClass(String className, Integer studentId) {
        Optional<User> student = userService.getUserById(studentId);
        String studentSchoolName = student.get().getSchoolName();
        List<Quiz> quizList = quizRepository.findByClassName(className);
        List <Quiz> quizListFilter = new ArrayList<>();
        for (Quiz quiz1 : quizList) {
            if (quiz1.getSchoolName() != null){
                if (quiz1.getSchoolName().equals(studentSchoolName)){
                    quizListFilter.add(quiz1);
                }
            }
        }
        return quizListFilter;
    }
    public List<Question> checkQuizCorrectAnswer(Integer quizId) {
      Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
       List <Question> questionList =  quiz.getQuestions();
       Map<Integer,String> answerList = new HashMap<>();
       answerList.put(1,"answer1");
        Stream<Question> questionStream = questionList.stream().filter(question -> question.getCorrectAnswer().equals(answerList.get(1)));
        return questionList;
    }
    public Answer saveQuizAnswer (Integer quizId, Integer userId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        Answer answer = new Answer();
        answer.setQuizId(quizId);
        answer.setUserId(userId);
       // answer.setAnswerList(answersMap);
        answerRepository.save(answer);
        return answer;
    }
}
