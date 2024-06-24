package com.findyourguide.api.State.Purchase;

import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;

public class RevokedState implements PurchasedState {

    @Override
    public void process(PurchasedService service) throws Exception {
        // TODO Logica de negocios de revokado de servicio
        System.out.println("You dont have any balance in your wallet");
    }

    @Override
    public void printStatus() {
        System.out.println("Service is Confirmed.");
    }
}