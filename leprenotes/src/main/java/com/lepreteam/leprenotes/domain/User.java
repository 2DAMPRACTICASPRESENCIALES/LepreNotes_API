package com.lepreteam.leprenotes.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotNull
    @NotBlank(message = "username is required")
    private String username;

    @Column
    @NotNull
    @NotBlank(message = "email is required")
    private String email;

    @Column
    @NotNull
    @NotBlank(message = "password is required")
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user_note")
    private List<Note> notes;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user_review")
    private List<Note> reviews;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user_order")
    private List<Order> orders;
}
