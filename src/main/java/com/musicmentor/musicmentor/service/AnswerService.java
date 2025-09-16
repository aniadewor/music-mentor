package com.musicmentor.musicmentor.service;

import com.musicmentor.musicmentor.model.Answer;
import com.musicmentor.musicmentor.model.Question;
import com.musicmentor.musicmentor.model.Quiz;
import com.musicmentor.musicmentor.repository.AnswerRepository;
import com.musicmentor.musicmentor.repository.QuestionRepository;
import com.musicmentor.musicmentor.repository.QuizRepository;
import com.musicmentor.musicmentor.response.AnswerResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuizRepository quizRepository;

    public AnswerResponse checkQuizCorrectAnswer(Integer userId, Integer quizId) {
        int scoreSum=0;
        AnswerResponse answerResponse = new AnswerResponse();
        Answer answer = answerRepository
                .findByUserIdAndQuizId(userId, quizId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Answer not found"));
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<Question> questionList =  quiz.getQuestions().stream().sorted(Comparator.comparing(Question::getId)).toList();
        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            String studentAnswer = answer.getStudentAnswerList().get(i);

            if (Objects.equals(studentAnswer, question.getCorrectAnswer())) {
                scoreSum += question.getScore();
            }
        }
        answerResponse.setQuizScore(quiz.getScoreSum());
        answerResponse.setScoreSum(scoreSum);
        answer.setCorrectAnswerScoreSum(scoreSum);
        answerRepository.save(answer);
        return answerResponse;
    }
    public Answer saveQuizAnswer (Integer quizId, Integer userId, List <Integer> questionIdList, List <String> studentAnswerList) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        Answer answer = new Answer();
        answer.setQuizId(quizId);
        answer.setUserId(userId);
        answer.setStudentAnswerList(studentAnswerList);
        answerRepository.save(answer);
        return answer;
    }

}
