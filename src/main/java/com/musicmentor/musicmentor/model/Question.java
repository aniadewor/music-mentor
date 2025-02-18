package com.musicmentor.musicmentor.model;

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
    private Quiz quizId;
    @NonNull
    private String question;
    @NonNull
    private String answer;
    private int score;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
}
