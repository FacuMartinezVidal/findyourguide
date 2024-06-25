package com.findyourguide.api.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.findyourguide.api.Strategis.Observer.IObserver;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.Reviews.Review;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tourist extends User {
    private String touristCode;

    @JsonManagedReference
    @OneToMany(mappedBy = "tourist", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PurchasedService> purchasedService;

    @Column(name = "balance", nullable = false)
    public Double balance;

    @JsonManagedReference
    @OneToMany(mappedBy = "tourist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> givenReviews;

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    public boolean hasSufficientFunds(Long purchaseAmount) {
        return this.getBalance() >= purchaseAmount;
    }

    public boolean refundFunds(Long amount) {
        this.setBalance((this.getBalance() + amount));
        return true;
    }

    @Transient
    private List<IObserver> observers = new ArrayList<>();

    public void addReview(Review review) {
        this.givenReviews.add(review);
        notifyObservers();
    }

    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    public void unregisterObserver(IObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(this);
        }
    }
}
