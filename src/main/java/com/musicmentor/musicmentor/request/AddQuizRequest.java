package com.musicmentor.musicmentor.request;

import com.musicmentor.musicmentor.model.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddQuizRequest {
    public Integer quizId;
    public String title;
    public String description;
    public Integer ownerId;
    public int numberOfQuestions;
    public List<QuestionRequest> questionList;
}
