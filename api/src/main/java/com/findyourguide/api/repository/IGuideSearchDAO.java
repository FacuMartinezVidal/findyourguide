package com.findyourguide.api.repository;

import com.findyourguide.api.entity.Guide;

import java.util.List;

public interface IGuideSearchDAO {
    List<Guide> findAllByCriteria(SearchRequest request);
}
