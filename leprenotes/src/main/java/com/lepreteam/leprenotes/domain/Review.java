package com.lepreteam.leprenotes.domain;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    @NotBlank(message = "description is required")
    private String description;

    @Column
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate postDate;

    @Column
    @NotNull
    @NotBlank(message = "rating is required")
    private int rating;

    @Column
    @NotNull
    private boolean isReported = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
