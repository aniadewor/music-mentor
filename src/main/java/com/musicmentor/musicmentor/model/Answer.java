package com.musicmentor.musicmentor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="answers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question questionId;
    private Integer quizId;
    private Integer userId;
    @ElementCollection
    private List<UserAnswer> answerList = new ArrayList<>();
    private boolean isCorrect;
}
