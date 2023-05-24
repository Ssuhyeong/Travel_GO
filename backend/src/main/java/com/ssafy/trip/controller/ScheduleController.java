package com.ssafy.trip.controller;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@RestController
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<?> addSchedule(
            @RequestBody List<Attraction>[] addList,
            @RequestParam Integer scheduleNum,
            @RequestParam(required = false) Integer day,
            Authentication authentication) {

        String userId = authentication.getName();

        scheduleService.addSchedule(addList,scheduleNum,userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listOfSchedule(Authentication authentication , @RequestParam Integer scheduleNum) {
        String userId = authentication.getName();

        List<Attraction>[] attractionList = scheduleService.scheduleOfDay(userId , scheduleNum);

        return new ResponseEntity<>(attractionList,HttpStatus.OK);
    }
}
