package com.findyourguide.api.State.Purchase;

import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;

public class RefundState implements PurchasedState {

    @Override
    public void process(PurchasedService service) throws Exception {
        service.getTourist().refundFunds(service.getService().getPrice());
    }

    @Override
    public void printStatus() {
        System.out.println("Service is Confirmed.");
    }
}