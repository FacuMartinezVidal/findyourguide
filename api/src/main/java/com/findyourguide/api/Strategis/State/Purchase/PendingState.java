package com.findyourguide.api.Strategis.State.Purchase;

import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedStatus;

public class PendingState implements PurchasedState {

    @Override
    public void process(PurchasedService service) throws Exception {
        if (service.getTourist().hasSufficientFunds(service.getService().getPrice())) {
            service.setStatus(PurchasedStatus.CONFIRMED);
            service.setState(new ConfirmedState());
        } else {
            service.setStatus(PurchasedStatus.REVOKED);
            service.setState(new RevokedState());
        }
    }

    @Override
    public void printStatus() {
        System.out.println("Service is Pending confirmation.");
    }

}