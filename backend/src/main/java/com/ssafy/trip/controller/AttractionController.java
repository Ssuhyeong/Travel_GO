package com.ssafy.trip.controller;


import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.service.AttractionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/attraction")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @GetMapping("/search-list")
    public ResponseEntity<?> selectByKeyword(
            @PageableDefault(size = 15) Pageable pageable,
            @RequestParam(value = "sidoCode",required = false) Integer sidoCode,
            @RequestParam(value = "contentTypeId", required = false) Integer contentTypeId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "contentId", required = false) Integer contentId,
            Principal principal
    ){

        String userId = principal.getName();
        Page<Attraction> attractions = attractionService.getAttractions(pageable,sidoCode,contentTypeId,keyword, contentId);

        return new ResponseEntity<>(attractions,HttpStatus.OK);

    }

    @GetMapping("/like")
    public ResponseEntity<?> attractionLike(
            @PageableDefault(size = 15) Pageable pageable,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "sidoCode",required = false) Integer sidoCode,
            Authentication authentication
    ){
        String userId = authentication.getName();
        Page<Attraction> attractions = attractionService.getLikeOrderBy(pageable,keyword,sidoCode);

        return new ResponseEntity<>(attractions,HttpStatus.OK);

    }

}
