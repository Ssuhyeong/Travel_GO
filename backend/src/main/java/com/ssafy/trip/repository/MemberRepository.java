package com.ssafy.trip.repository;

import com.ssafy.trip.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Integer> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByName(String name);
    Optional<Member> findByRefreshToken(String refreshToken);
}
