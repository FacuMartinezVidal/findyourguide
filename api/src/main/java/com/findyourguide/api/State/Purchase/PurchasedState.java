package com.findyourguide.api.State.Purchase;

import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;

public interface PurchasedState {
    void process(PurchasedService service) throws Exception;

    void printStatus();
}
