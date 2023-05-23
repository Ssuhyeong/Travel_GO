package com.ssafy.trip.controller;


import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.service.AttractionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
            @RequestParam(value = "contentTypeId", required = false) Integer contentTypeId,
            Authentication authentication
    ){
        String userId = authentication.getName();
        Page<Attraction> attractions = attractionService.getLikeOrderBy(pageable,keyword,sidoCode,contentTypeId);

        return new ResponseEntity<>(attractions,HttpStatus.OK);

    }

    @GetMapping("/bestlike")
    public ResponseEntity<?> bestLike() {
        List<Attraction> best = attractionService.bestLike();
        return new ResponseEntity<>(best,HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<?> distanceSort(
            @PageableDefault(size = 15) Pageable pageable,
            @RequestParam String latitude,
            @RequestParam String longitude,
            @RequestParam(required = false) String keyword,
            Authentication authentication
            ){

        String userId = authentication.getName();

        List<Attraction> distanceList = attractionService.distance(keyword,latitude,longitude);

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), distanceList.size());

        Page<Attraction> attractionPage = new PageImpl<>(distanceList.subList(start,end),pageable, distanceList.size());

        return new ResponseEntity<>(attractionPage,HttpStatus.OK);
    }

}
