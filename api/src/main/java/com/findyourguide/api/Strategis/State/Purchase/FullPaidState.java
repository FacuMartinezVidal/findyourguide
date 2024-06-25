package com.findyourguide.api.Strategis.State.Purchase;

import com.findyourguide.api.Strategis.Factory.StateFactory;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Role;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedStatus;

import com.findyourguide.api.service.interfaces.IUserService;
import com.findyourguide.api.util.UserValidations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FullPaidState implements PurchasedState {
    @Autowired
    private StateFactory stateFactory;
    private final IUserService userService;
    @Autowired
    private final UserValidations userValidations;

    public PurchasedService process(PurchasedService service, PurchasedStatus status, User user) {

        if (status.equals(PurchasedStatus.CANCELED)) {
            userValidations.validateRole(Role.TOURIST);
            Tourist tourist = (Tourist) user;
            userService.processRefound(tourist, service.getBalancePaid(), 10D);
            service.setBalancePaid(0D);
            service.setStatus(PurchasedStatus.CANCELED);
            service.setState(stateFactory.getState(PurchasedStatus.CANCELED));

        } else if (status.equals(PurchasedStatus.IN_TOUR)) {
            userValidations.validateRole(Role.GUIDE);
            service.setStatus(PurchasedStatus.IN_TOUR);
            service.setState(stateFactory.getState(PurchasedStatus.IN_TOUR));

        }
        return service;

    }

    @Override
    public void printStatus() {
        System.out.println("Service is Pending confirmation.");
    }
}
