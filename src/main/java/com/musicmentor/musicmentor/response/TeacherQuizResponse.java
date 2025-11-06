package com.musicmentor.musicmentor.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherQuizResponse {
    private String title;
    private int score;
    private int scoreSum;
    private String name;
    private String lastName;
}
