package com.lepreteam.leprenotes.domain;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private String code;

    @Column
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate orderDate;

    @Column
    @NotNull
    private boolean isCancelled = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;
}
