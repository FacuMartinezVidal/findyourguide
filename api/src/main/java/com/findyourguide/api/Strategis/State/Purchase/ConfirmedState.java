package com.findyourguide.api.Strategis.State.Purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.findyourguide.api.Strategis.Factory.StateFactory;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Role;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedStatus;

import com.findyourguide.api.service.interfaces.IUserService;
import com.findyourguide.api.util.UserValidations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ConfirmedState implements PurchasedState {

    private final IUserService userService;
    @Autowired
    private StateFactory stateFactory;
    @Autowired
    private final UserValidations userValidations;

    @Override
    @Transactional
    public PurchasedService process(PurchasedService service, PurchasedStatus status, User user) throws Exception {

        if (user instanceof Tourist) {
            Tourist tourist = (Tourist) user;
            userService.processPayment(tourist, service);
            service.setBalancePaid(service.getService().getPrice());
            service.setStatus(PurchasedStatus.FULL_PAID);
            service.setState(stateFactory.getState(PurchasedStatus.FULL_PAID));
        } else if (user instanceof Guide) {
            userValidations.validateRole(Role.TOURIST);
        }

        return service;
    }

    @Override
    public void printStatus() {
        System.out.println("Service is Confirmed.");
    }
}
