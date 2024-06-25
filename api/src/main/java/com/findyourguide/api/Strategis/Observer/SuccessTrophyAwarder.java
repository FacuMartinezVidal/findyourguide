package com.findyourguide.api.Strategis.Observer;

import java.util.Optional;

import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.Trophy.TrophyType;
import com.findyourguide.api.entity.Trophy.Trophy;
import com.findyourguide.api.service.interfaces.ITrophyService;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class SuccessTrophyAwarder implements IObserver {

    private final ITrophyService trophyService;

    @Override
    public void update(User user) {
        if (user instanceof Guide) {
            Guide guide = (Guide) user;
            Optional<Trophy> successTrophy = user.getTrophyList().stream()
                    .filter(trophy -> trophy.getType() == TrophyType.SUCCESS)
                    .findFirst();
            double averageRating = guide.calculateAverageRating();
            int totalReviews = guide.getReceivedReviews().size();

            if ((averageRating >= 4.5 && totalReviews >= 10) && !successTrophy.isPresent()) {
                System.out.println("Creating Success Trophy");
                trophyService.create(TrophyType.SUCCESS, "Success Trophy for maintaining a high rating", guide);
            }
            if (averageRating < 4.5 && successTrophy.isPresent()) {
                System.out.println("Deleting Success Trophy with ID: " + successTrophy.get().getId());
                // TODO Deberia eliminar pero no elimina, nose que mas checkear (3.16am)
                // trophyService.deleteById(successTrophy.get().getId());
            }
        }
    }

}
