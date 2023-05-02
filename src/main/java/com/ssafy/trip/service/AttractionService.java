package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.repository.AttractionRepository;
import com.ssafy.trip.repository.AttractionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionService {

    @Autowired
    private AttractionRepository attractionRepository;

    public List<Attraction> findAttraction(Integer sido_code, Integer content_type_id, String keyword){
        Specification<Attraction> specification = Specification.where(AttractionSpecification.findBySidoCode(sido_code));

        if(content_type_id != null){
            specification = specification.and(AttractionSpecification.ContainingContentType(content_type_id));
        }
        if(keyword != null) {
            specification = specification.and(AttractionSpecification.ContatingTitle(keyword));
        }



        return attractionRepository.findAll(specification);


    }

}
