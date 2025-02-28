package com.musicmentor.musicmentor.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quizId;
    @NonNull
    private String question;
    @NonNull
    private String answer1;
    @NonNull
    private String answer2;
    @NonNull
    private String answer3;
    @NonNull
    private String answer4;
    private String correctAnswer;
    private int score;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
}
