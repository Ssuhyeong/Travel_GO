package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Schedule;
import com.ssafy.trip.dto.request.ScheduleRequestDto;
import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.repository.Schedule.ScheduleRepository;
import com.ssafy.trip.repository.Schedule.TravelRoutesRepository;
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

    public void addSchedule(List<Attraction>[] addList, Integer scheduleNum, String userId) {
        Member member = memberRepository.findByEmail(userId).get();

        for(int i=1;i<=7;i++) {
            Integer day = i;
            int s = addList[i].size();
            for (int j=0;j<s;j++){
                Attraction attraction = addList[i].get(j);

                ScheduleRequestDto scheduleRequestDto = new ScheduleRequestDto();
                scheduleRequestDto.setMember(member);
                scheduleRequestDto.setDay(day);
                scheduleRequestDto.setAttraction(attraction);
                scheduleRequestDto.setScheduleNum(scheduleNum);

                Schedule schedule = scheduleRequestDto.toEntity();

                scheduleRepository.save(schedule);
            }

        }

    }

    public List<Attraction>[] scheduleOfDay(String userId,Integer scheduleNum){
        Member member = memberRepository.findByEmail(userId).get();
        List<Attraction>[] plan  = new ArrayList[8];
        for(int day=1;day<=7;day++){
            List<Attraction> attractionsOfDay = scheduleRepository.findByDayAndScheduleNum(userId,day,scheduleNum);
            plan[day] = attractionsOfDay;
        }

        return plan;
    }
}
