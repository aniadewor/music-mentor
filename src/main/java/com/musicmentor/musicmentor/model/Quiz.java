package com.musicmentor.musicmentor.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="quizzes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="quiz_id")
    private Integer id;
    @NonNull
    private String title;
    private String description;
    private int scoreSum;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;
    private int numberOfQuestions;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();
    @ElementCollection
    @Column(name = "class_name")
    private List<String> className = new ArrayList<>();
}
