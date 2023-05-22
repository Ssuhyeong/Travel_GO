package com.ssafy.trip.repository.heart;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Heart;
import com.ssafy.trip.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HeartRepository extends JpaRepository<Heart,Integer> , HeartRepositoryCustom {

      Optional<Heart> findByMemberAndAttraction(Member member , Attraction attraction);

}
