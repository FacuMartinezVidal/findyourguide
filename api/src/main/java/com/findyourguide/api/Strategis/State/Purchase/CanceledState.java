package com.findyourguide.api.Strategis.State.Purchase;

import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedStatus;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CanceledState implements PurchasedState {

    @Override
    public PurchasedService process(PurchasedService service, PurchasedStatus status, User user) throws Exception {
        return service;
    }

    @Override
    public void printStatus() {
        System.out.println("Service is Confirmed.");
    }
}
