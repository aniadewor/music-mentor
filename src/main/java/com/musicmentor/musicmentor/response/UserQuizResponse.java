package com.musicmentor.musicmentor.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuizResponse {
    private String title;
    private int score;
    private int scoreSum;
}
