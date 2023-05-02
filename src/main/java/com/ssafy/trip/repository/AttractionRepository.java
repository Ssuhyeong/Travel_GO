package com.ssafy.trip.repository;

import com.ssafy.trip.Entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction,Integer>, JpaSpecificationExecutor<Attraction> {

}
