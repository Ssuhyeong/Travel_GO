package com.ssafy.trip.controller;

import com.ssafy.trip.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/like")
@RestController
public class HeartController {

    private final HeartService likeService;

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
