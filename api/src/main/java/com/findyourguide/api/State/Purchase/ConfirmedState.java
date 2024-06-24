package com.findyourguide.api.State.Purchase;

import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedStatus;

public class ConfirmedState implements PurchasedState {

    @Override
    public void process(PurchasedService service) throws Exception {
        service.setStatus(PurchasedStatus.REFUND);
        service.setState(new RefundState());
    }

    @Override
    public void printStatus() {
        System.out.println("Service is Confirmed.");
    }
}
