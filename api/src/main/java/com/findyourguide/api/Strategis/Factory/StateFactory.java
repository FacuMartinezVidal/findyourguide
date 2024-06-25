package com.findyourguide.api.Strategis.Factory;

import org.springframework.stereotype.Component;

import com.findyourguide.api.Strategis.State.Purchase.CanceledState;
import com.findyourguide.api.Strategis.State.Purchase.ConfirmedState;
import com.findyourguide.api.Strategis.State.Purchase.EndState;
import com.findyourguide.api.Strategis.State.Purchase.FullPaidState;
import com.findyourguide.api.Strategis.State.Purchase.InTourState;
import com.findyourguide.api.Strategis.State.Purchase.PendingState;
import com.findyourguide.api.Strategis.State.Purchase.PurchasedState;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Component
public class StateFactory {
    @Autowired
    private ApplicationContext context;

    public PurchasedState getState(PurchasedStatus status) {
        switch (status) {
            case PENDING:
                return context.getBean(PendingState.class);
            case FULL_PAID:
                return context.getBean(FullPaidState.class);
            case CONFIRMED:
                return context.getBean(ConfirmedState.class);
            case CANCELED:
                return context.getBean(CanceledState.class);
            case END:
                return context.getBean(EndState.class);
            case INTOUR:
                return context.getBean(InTourState.class);
            default:
                throw new IllegalStateException("Unknown state: " + status);
        }
    }
}
