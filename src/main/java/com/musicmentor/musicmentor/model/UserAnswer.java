package com.musicmentor.musicmentor.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class UserAnswer {
    private Integer questionId;
    private String answer;
}
