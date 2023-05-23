package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Schedule;
import com.ssafy.trip.Entity.TravelRoutes;
import com.ssafy.trip.dto.request.TravelRoutesRequestDto;
import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.repository.Schedule.TravelRoutesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TravelRoutesService {

    private final TravelRoutesRepository travelRoutesRepository;
    private final MemberRepository memberRepository;

    public void addTravelRoutes(TravelRoutesRequestDto travelRoutesRequestDto, String userId) {

        Member member = memberRepository.findByEmail(userId).get();
        travelRoutesRequestDto.setMember(member);

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


}
