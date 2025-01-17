package com.musicmentor.musicmentor.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String password;
   // @Enumerated(EnumType.STRING)
    private Role role;
}

