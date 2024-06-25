package com.findyourguide.api.entity.PurchasedServiceEntitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.findyourguide.api.Strategis.State.Purchase.PendingState;
import com.findyourguide.api.Strategis.State.Purchase.PurchasedState;
import com.findyourguide.api.entity.Base;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.Service.Service;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "purchased_service")
public class PurchasedService extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "tourist_id", nullable = false)
    private Tourist tourist;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PurchasedStatus status;

    @Column(name = "balance")
    private Double balancePaid;

    @Transient
    private PurchasedState state;

    public PurchasedService nextState(PurchasedStatus status, User user) throws Exception {
        return state.process(this, status, user);
    }

    public void setState(PurchasedState state) {
        this.state = state;
    }
}
