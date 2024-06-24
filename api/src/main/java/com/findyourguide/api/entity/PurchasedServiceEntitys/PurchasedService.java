package com.findyourguide.api.entity.PurchasedServiceEntitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.findyourguide.api.State.Purchase.ConfirmedState;
import com.findyourguide.api.State.Purchase.PendingState;
import com.findyourguide.api.State.Purchase.PurchasedState;
import com.findyourguide.api.State.Purchase.RefundState;
import com.findyourguide.api.State.Purchase.RevokedState;
import com.findyourguide.api.entity.Base;
import com.findyourguide.api.entity.Tourist;
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
    private PurchasedStatus status = PurchasedStatus.PENDING;

    @Transient
    private PurchasedState state;

    @PostLoad
    private void initializeState() {
        switch (status) {
            case PENDING:
                this.state = new PendingState();
                break;
            case CONFIRMED:
                this.state = new ConfirmedState();
                break;
            case REVOKED:
                this.state = new RevokedState();
                break;
            case REFUND:
                this.state = new RefundState();
                break;
            default:
                throw new IllegalStateException("Unknown state");
        }
    }

    public PurchasedService() {
        this.state = new PendingState(); // Estado inicial
    }

    public void nextState() throws Exception {
        state.process(this);
    }

    public void setState(PurchasedState state) {
        this.state = state;
    }
}
