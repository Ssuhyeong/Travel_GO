package com.ssafy.trip.controller;


import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.service.AttractionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attraction")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @GetMapping("/search-list")
    public ResponseEntity<?> selectByKeyword(
            @RequestParam(value = "sido_code") Integer sido_code,
            @RequestParam(value = "content_type_id", required = false) Integer content_type_id,
            @RequestParam(value = "keyword", required = false) String keyword
    ){

      // log.debug("SIDO: "+sido_code + " TYPE: "+content_type_id+" KEY: "+keyword);
        List<Attraction> attractionList = attractionService.findAttraction(sido_code, content_type_id, keyword);
       // log.debug(attractionList.toString());

        if(attractionList.isEmpty()){
            HttpStatus status = HttpStatus.NOT_FOUND;
            log.debug("STATUS: " + String.valueOf(HttpStatus.NOT_FOUND));
            return ResponseEntity.status(404).build();
        }
        else{
            log.debug("STATUS : " + String.valueOf(HttpStatus.OK));
            return ResponseEntity.ok().body(attractionList);
        }

        // HttpStatus status = attractionList.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;

      //  return new ResponseEntity<List<Attraction>>(attractionList, status);
    }

}
