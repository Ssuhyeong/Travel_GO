package com.ssafy.trip.repository.Schedule;

import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Schedule;
import com.ssafy.trip.Entity.TravelRoutes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelRoutesRepository extends JpaRepository<TravelRoutes, Integer> , TravelRoutesRepositoryCustom{

    List<TravelRoutes> findByMember(Member member);

    List<TravelRoutes> findByMember_Email(String userId);
}
