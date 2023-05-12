package com.lepreteam.leprenotes.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    @NotBlank(message = "title is required")
    private String title;

    @Column
    @NotNull
    @NotBlank(message = "subject is required")
    private String subject;

    @Column
    @NotNull
    @NotBlank(message = "content is required")
    private byte[] content;

    @Column
    @NotNull
    @NotBlank(message = "school year is required")
    private String schoolYear;

    @Column
    @NotNull
    @NotBlank(message = "price is required")
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "note")
    @JsonBackReference(value = "note_order")
    private List<Order> orders;
}
