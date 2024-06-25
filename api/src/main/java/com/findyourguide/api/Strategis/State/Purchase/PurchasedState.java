package com.findyourguide.api.Strategis.State.Purchase;

import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedStatus;

public interface PurchasedState {
    PurchasedService process(PurchasedService service, PurchasedStatus status, User user) throws Exception;

    void printStatus();
}
