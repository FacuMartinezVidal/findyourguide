package com.findyourguide.api.Strategis.Observer;

import java.util.Optional;

import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.Trophy.TrophyType;
import com.findyourguide.api.entity.Trophy.Trophy;
import com.findyourguide.api.service.interfaces.ITrophyService;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ReviewTrophyAwarder implements IObserver {

    private final ITrophyService trophyService;

    @Override
    public void update(User user) {
        if (user instanceof Tourist) {
            Tourist tourist = (Tourist) user;
            Optional<Trophy> reviewTrophy = user.getTrophyList().stream()
                    .filter(trophy -> trophy.getType() == TrophyType.REVIEW)
                    .findFirst();
            if (!reviewTrophy.isPresent() && tourist.getGivenReviews().size() >= 10) {
                System.out.println("Creating Review Trophy");
                trophyService.create(TrophyType.REVIEW, "Review Trophy for writing more than 10 reviews.", tourist);
            }
            if (reviewTrophy.isPresent() && tourist.getGivenReviews().size() < 10) {
                System.out.println("Deleting Review Trophy with ID: " + reviewTrophy.get().getId());
                // TODO Deberia eliminar pero no elimina, nose que mas checkear (3.16am)
                // trophyService.deleteById(reviewTrophy.get().getId());
            }
        }
    }
}
