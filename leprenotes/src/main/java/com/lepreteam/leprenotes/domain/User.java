package com.lepreteam.leprenotes.domain;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "notes")
    @JsonBackReference(value = "user_note")
    private List<Note> notes;

    @OneToMany(mappedBy = "reviews")
    @JsonBackReference(value = "user_review")
    private List<Note> reviews;

    @OneToMany(mappedBy = "orders")
    @JsonBackReference(value = "user_order")
    private List<Order> orders;
}
