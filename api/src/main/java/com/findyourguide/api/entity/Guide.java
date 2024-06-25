package com.findyourguide.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.findyourguide.api.Strategis.Observer.IObserver;
import com.findyourguide.api.entity.Reviews.Review;
import com.findyourguide.api.entity.Service.Service;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Guide extends User {

    @Column(name = "Country")
    private String country;

    @Column(name = "credential_photo")
    private String credentialPhoto;

    @Enumerated(EnumType.STRING)
    private Language language;

    @JsonManagedReference
    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Service> guideServices;

    private Double score;

    @JsonManagedReference
    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> receivedReviews;

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Transient
    private List<IObserver> observers = new ArrayList<>();

    public void addReview(Review review) {
        this.receivedReviews.add(review);
        this.setScore(this.calculateAverageRating());
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

    public double calculateAverageRating() {
        double average = this.getReceivedReviews().stream()
                .mapToInt(Review::getScore)
                .average()
                .orElse(0.0);

        return Math.round(average * 100.0) / 100.0;
    }

}
