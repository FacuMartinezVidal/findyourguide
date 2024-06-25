package com.findyourguide.api.repository;

import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Service.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class GuideSearchDAO implements IGuideSearchDAO {
    private final EntityManager entityManager;

    public List<Guide> findAllByCriteria(SearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Guide> criteriaQuery = criteriaBuilder.createQuery(Guide.class);
        Root<Guide> guideRoot = criteriaQuery.from(Guide.class);
        Join<Guide, Service> serviceJoin = guideRoot.join("guideServices");

        List<Predicate> predicates = new ArrayList<>();

        if (request.getFirstName() != null) {
            predicates.add(criteriaBuilder.like(guideRoot.get("firstName"), "%" + request.getFirstName() + "%"));
        }
        if (request.getLastName() != null) {
            predicates.add(criteriaBuilder.like(guideRoot.get("lastName"), "%" + request.getLastName() + "%"));
        }
        if (request.getLanguage() != null) {
            predicates.add(criteriaBuilder.equal(guideRoot.get("language"), request.getLanguage()));
        }
        if (request.getCity() != null) {
            predicates.add(criteriaBuilder.equal(serviceJoin.get("city"), request.getCity()));
        }
        if (request.getCountry() != null) {
            predicates.add(criteriaBuilder.like(guideRoot.get("country"), "%" + request.getCountry() + "%"));
        }
        if (request.getServiceType() != null) {
            predicates.add(criteriaBuilder.equal(serviceJoin.get("serviceType"), request.getServiceType()));
        }
        if (request.getScore() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(guideRoot.get("score"), request.getScore()));
        }

        criteriaQuery.select(guideRoot).distinct(true).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Guide> query = entityManager.createQuery(criteriaQuery);

        List<Guide> guides = query.getResultList();

        for (Guide guide : guides) {
            List<Service> filteredServices = guide.getGuideServices().stream()
                    .filter(service ->
                            (request.getServiceType() == null || service.getServiceType().equals(request.getServiceType())) &&
                                    (request.getCity() == null || service.getCity().equals(request.getCity()))
                    ).collect(Collectors.toList());
            guide.setGuideServices(filteredServices);
        }


        return guides;
    }
}
