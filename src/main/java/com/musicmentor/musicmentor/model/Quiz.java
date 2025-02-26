package com.musicmentor.musicmentor.model;

import jakarta.persistence.*;
import lombok.*;
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
    private int score;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;
    @ManyToMany
    @JoinTable(
            name = "quiz_users",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}
