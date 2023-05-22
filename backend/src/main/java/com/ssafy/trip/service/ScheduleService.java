package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Schedule;
import com.ssafy.trip.dto.request.ScheduleRequestDto;
import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.repository.Schedule.ScheduleRepository;
import com.ssafy.trip.repository.attracion.AttractionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;


    public void addSchedule(Integer day, Integer attractionId, String userId) {
        Member member = memberRepository.findByEmail(userId).get();
        Attraction attraction = attractionRepository.findById(attractionId).get();

        ScheduleRequestDto scheduleRequestDto = new ScheduleRequestDto();
        scheduleRequestDto.setMember(member);
        scheduleRequestDto.setDay(day);
        scheduleRequestDto.setAttraction(attraction);

        Schedule schedule = scheduleRequestDto.toEntity();

        scheduleRepository.save(schedule);
    }

    public List<Attraction>[] scheduleOfDay(String userId){
        Member member = memberRepository.findByEmail(userId).get();
        List<Attraction>[] plan  = new ArrayList[8];
        for(int day=1;day<=7;day++){
            List<Attraction> attractionsOfDay = scheduleRepository.findByDay(userId,day);
            plan[day] = attractionsOfDay;
        }

        return plan;
    }
}
