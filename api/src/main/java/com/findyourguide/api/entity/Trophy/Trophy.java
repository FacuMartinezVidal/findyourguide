package com.findyourguide.api.entity.Trophy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.findyourguide.api.entity.Base;
import com.findyourguide.api.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TrophyType type;

}
