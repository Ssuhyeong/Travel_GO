package com.ssafy.trip.controller;

import com.ssafy.trip.repository.heart.HeartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ProfileController {

    private final HeartRepository heartRepository;

    @GetMapping("/")
    public ResponseEntity<?> homeLike(Authentication authentication){
        List<Integer> attractionLikeList = heartRepository.findBySearch(authentication.getName());
        return new ResponseEntity<>(attractionLikeList, HttpStatus.OK);
    }

    @GetMapping("/profilepage")
    public ResponseEntity<?> profileLike(Authentication authentication){
        List<Integer> attractionLikeList = heartRepository.findBySearch(authentication.getName());
        return new ResponseEntity<>(attractionLikeList, HttpStatus.OK);
    }

    @GetMapping("/updateScheduleView")
    public ResponseEntity<?> scheduleLike(Authentication authentication){
        List<Integer> attractionLikeList = heartRepository.findBySearch(authentication.getName());
        return new ResponseEntity<>(attractionLikeList, HttpStatus.OK);
    }
}
