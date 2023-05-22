package com.ssafy.trip.controller;

import com.ssafy.trip.repository.heart.HeartRepository;
import com.ssafy.trip.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/like")
@RestController
public class HeartController {

    private final HeartService likeService;
    private final HeartRepository heartRepository;


    @GetMapping
    public ResponseEntity<?> myLike(Authentication authentication){
        List<Integer> attractionLikeList = heartRepository.findBySearch(authentication.getName());
        return new ResponseEntity<>(attractionLikeList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addLike(@RequestParam Integer attractionId , Authentication authentication) throws Exception {

        String userId = authentication.getName();
        likeService.addLike(attractionId,userId);

        return new ResponseEntity<Object>(HttpStatus.OK) ;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLike(@RequestParam Integer attractionId , Authentication authentication)throws Exception {
        String userId = authentication.getName();
        likeService.deleteLike(attractionId,userId);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
