package com.musicmentor.musicmentor.response;

import com.musicmentor.musicmentor.model.Question;
import com.musicmentor.musicmentor.request.QuestionRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizResponse {
    private Integer id;
    private String title;
    private String description;
    private int score;
    public List<Question> questionList;

}
