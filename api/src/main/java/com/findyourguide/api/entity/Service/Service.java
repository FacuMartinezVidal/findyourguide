package com.findyourguide.api.entity.Service;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.findyourguide.api.entity.Base;
import com.findyourguide.api.entity.Guide;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "service")
public class Service extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType serviceType;

    @Column(nullable = false)
    private Long price;
    private int rate;
    @Column(nullable = false)
    private int quantity;
    private String description;

    private String city;
    private String country;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "guide_id")
    private Guide guide;

}
