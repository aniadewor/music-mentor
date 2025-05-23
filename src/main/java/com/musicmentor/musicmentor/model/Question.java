package com.musicmentor.musicmentor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @JsonIgnore
    private Quiz quiz;
    @NonNull
    private String questionTitle;
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
