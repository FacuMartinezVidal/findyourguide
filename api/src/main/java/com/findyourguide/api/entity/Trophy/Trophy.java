package com.findyourguide.api.entity.Trophy;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.findyourguide.api.entity.Base;
import com.findyourguide.api.entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "trophy")
public class Trophy extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "condition", nullable = false)
    private String condition;

}
