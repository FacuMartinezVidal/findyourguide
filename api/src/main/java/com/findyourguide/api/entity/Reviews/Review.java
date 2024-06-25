package com.findyourguide.api.entity.Reviews;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.findyourguide.api.entity.Base;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Tourist;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "tourist_id", nullable = false)
    private Tourist tourist;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "guide_id", nullable = false)
    private Guide guide;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "description", nullable = false)
    private String description;

}
