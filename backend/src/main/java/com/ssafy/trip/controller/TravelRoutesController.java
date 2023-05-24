package com.ssafy.trip.controller;

import com.ssafy.trip.Entity.Schedule;
import com.ssafy.trip.Entity.TravelRoutes;
import com.ssafy.trip.service.TravelRoutesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/travel")
@RestController
public class TravelRoutesController {

    private final TravelRoutesService travelRoutesService;

//    @PostMapping
//    public ResponseEntity<?> addTravelRoutes(
//            @RequestParam String title,
//            @RequestParam Integer scheduleInfo,
//            Authentication authentication
//    ){
//        String userId = authentication.getName();
//        travelRoutesService.addTravelRoutes(title,scheduleInfo,userId);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<?> listTravelRoutes(Authentication authentication){
        String userId = authentication.getName();

        List<TravelRoutes> myTravel = travelRoutesService.travelRouteInfo(userId);
        System.out.println("myTravel = " + myTravel);

        return new ResponseEntity<>(myTravel,HttpStatus.OK);
    }

    @GetMapping("/{scheduleInfo}")
    public ResponseEntity<?> detailTravelInfo(
            @PathVariable Integer scheduleInfo,
            Authentication authentication
    ){
        String userId = authentication.getName();
        List<Schedule> scheduleDetailList = travelRoutesService.scheduleListSearch(userId,scheduleInfo);

        return new ResponseEntity<>(scheduleDetailList, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTravel(
            @RequestParam Integer scheduleInfo,
            Authentication authentication
    ){
        String userId = authentication.getName();

        travelRoutesService.deleteTravel(userId,scheduleInfo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
