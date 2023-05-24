package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Schedule;
import com.ssafy.trip.Entity.TravelRoutes;
import com.ssafy.trip.dto.request.TravelRoutesRequestDto;
import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.repository.Schedule.ScheduleRepository;
import com.ssafy.trip.repository.Schedule.TravelRoutesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TravelRoutesService {

    private final TravelRoutesRepository travelRoutesRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public void addTravelRoutes(String title,Integer scheduleInfo, String userId) {

        Member member = memberRepository.findByEmail(userId).get();
        TravelRoutesRequestDto travelRoutesRequestDto = new TravelRoutesRequestDto();
        travelRoutesRequestDto.setMember(member);
        travelRoutesRequestDto.setTitle(title);
        travelRoutesRequestDto.setScheduleInfo(scheduleInfo);

        TravelRoutes travelRoutes = travelRoutesRequestDto.toEntity();

        travelRoutesRepository.save(travelRoutes);

    }

    public List<Schedule> scheduleListSearch(String userId, Integer scheduleInfo){
        return travelRoutesRepository.findByEamilAndScheduleInfo(userId,scheduleInfo);
    }

    public List<TravelRoutes> travelRouteInfo(String userId){
        List<TravelRoutes> routesList = travelRoutesRepository.findByMember_Email(userId);

        return routesList;
    }

    @Transactional
    public void deleteTravel(String userId , Integer travelInfo) {
        TravelRoutes travelRoutes = travelRoutesRepository.findByEamilAndScheduleNum(userId,travelInfo);
        List<Schedule> scheduleList = scheduleRepository.findByEmailAndScheduleNum(userId,travelInfo);

        for (int i=0;i<scheduleList.size();i++){
            Schedule schedule = scheduleList.get(i);
            if(schedule != null){
                scheduleRepository.deleteById(schedule.getId());
            }
            else {
                break;
            }

        }

        log.info("travelRoutes 값 : {}", travelRoutes);
        if(travelRoutes != null) {
            travelRoutesRepository.deleteById(travelRoutes.getId());
        }


    }


}
