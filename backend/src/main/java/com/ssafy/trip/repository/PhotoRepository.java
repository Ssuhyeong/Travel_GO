package com.ssafy.trip.repository;

import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo,Long> {

    Optional<Photo> findByMember(Member member);
}
