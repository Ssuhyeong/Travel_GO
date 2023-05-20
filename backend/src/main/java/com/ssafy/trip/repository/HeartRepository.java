package com.ssafy.trip.repository;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Heart;
import com.ssafy.trip.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HeartRepository extends JpaRepository<Heart,Integer> {

      Optional<Heart> findByMemberAndAttraction(Member member , Attraction attraction);

}
