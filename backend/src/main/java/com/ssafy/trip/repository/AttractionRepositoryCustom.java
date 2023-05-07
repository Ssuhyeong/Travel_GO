package com.ssafy.trip.repository;

import com.ssafy.trip.Entity.Attraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AttractionRepositoryCustom {

    Page<Attraction> findBySearchOption(Pageable pageable, Integer sidoCode, Integer contentTypeId, String keyword);

}
