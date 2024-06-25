package com.findyourguide.api.Strategis.State.Purchase;

import com.findyourguide.api.Strategis.Factory.StateFactory;
import com.findyourguide.api.entity.Role;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedStatus;
import com.findyourguide.api.util.UserValidations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InTourState implements PurchasedState {

    @Autowired
    private StateFactory stateFactory;
    @Autowired
    private final UserValidations userValidations;

    @Override
    public PurchasedService process(PurchasedService service, PurchasedStatus status, User user) {
        userValidations.validateRole(Role.GUIDE);
        service.setStatus(PurchasedStatus.END);
        service.setState(stateFactory.getState(PurchasedStatus.END));
        return service;

    }

    @Override
    public void printStatus() {
        System.out.println("Service is Confirmed.");
    }
}