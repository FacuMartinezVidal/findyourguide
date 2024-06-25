package com.findyourguide.api.Strategis.State.Purchase;

import com.findyourguide.api.Strategis.Factory.StateFactory;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Role;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedStatus;
import com.findyourguide.api.util.UserValidations;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PendingState implements PurchasedState {
    @Autowired
    private StateFactory stateFactory;
    @Autowired
    private final UserValidations userValidations;

    @Override
    public PurchasedService process(PurchasedService service, PurchasedStatus status, User user) throws Exception {

        if (user instanceof Guide) {
            switch (status) {
                case CONFIRMED:
                    service.setStatus(PurchasedStatus.CONFIRMED);
                    service.setState(stateFactory.getState(PurchasedStatus.CONFIRMED));
                    break;
                case CANCELED:
                    service.setStatus(PurchasedStatus.CANCELED);
                    service.setState(stateFactory.getState(PurchasedStatus.CANCELED));
                    break;
                default:
                    throw new IllegalArgumentException();

            }
        } else if (user instanceof Tourist) {
            userValidations.validateRole(Role.GUIDE);
        }
        return service;

    }

    @Override
    public void printStatus() {
        System.out.println("Service is Pending confirmation.");
    }

}