package com.ssafy.trip.controller;

import com.ssafy.trip.dto.request.HeartRequestDto;
import com.ssafy.trip.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/like")
@RestController
public class HeartController {

    private final HeartService likeService;

    @PostMapping
    public ResponseEntity<?> addLike(@RequestParam Integer attractionId , Principal principal) throws Exception {

        String userId = principal.getName();
        likeService.addLike(attractionId,userId);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLike(@RequestParam Integer attractionId , Principal principal) throws Exception {

        String userId = principal.getName();
        likeService.deleteLike(attractionId,userId);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
