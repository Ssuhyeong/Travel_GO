package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AttractionService {

    @Autowired
    private AttractionRepository attractionRepository;

    public Page<Attraction> getAttractions(Pageable pageable, Integer sidoCode, Integer contentTypeId, String keyword, Integer contentId) {
        return attractionRepository.findBySearchOption(pageable, sidoCode,contentTypeId,keyword, contentId);
    }

}
