package com.ssafy.trip.repository;

import com.ssafy.trip.Entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
