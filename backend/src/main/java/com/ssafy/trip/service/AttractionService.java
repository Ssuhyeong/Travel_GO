package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Attraction;

import com.ssafy.trip.repository.attracion.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;

    public Page<Attraction> getAttractions(
            Pageable pageable, Integer sidoCode, Integer contentTypeId, String keyword, Integer contentId
    ) {
        return attractionRepository.findBySearchOption(pageable, sidoCode,contentTypeId,keyword, contentId);
    }

    public Page<Attraction> getLikeOrderBy(
            Pageable pageable, String keyword , Integer sidoCode
    ) {
        return attractionRepository.findBySearchOrderBy(pageable, keyword,sidoCode);
    }

}
