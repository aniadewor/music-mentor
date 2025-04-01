package com.musicmentor.musicmentor.request;

import com.musicmentor.musicmentor.model.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddQuestionRequest {
    public Integer quizId;
    public List<QuestionRequest> questionList;
}
