package com.musicmentor.musicmentor.request;

import com.musicmentor.musicmentor.model.QuestionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {

    private String questionTitle;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;
    private int score;
    private QuestionType type;
}
