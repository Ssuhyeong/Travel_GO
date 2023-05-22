package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Heart;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.dto.request.HeartRequestDto;
import com.ssafy.trip.repository.heart.HeartRepository;
import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.repository.attracion.AttractionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository likeRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;

    @Transactional
    public void addLike(Integer attractionId , String userId) throws Exception{

        Member member = memberRepository.findByEmail(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다"));
        Attraction attraction = attractionRepository.findById(attractionId)
                .orElseThrow(() -> new Exception("해당하는 관광지를 찾을 수 없습니다."));

        HeartRequestDto heartRequestDto = new HeartRequestDto();
        heartRequestDto.setMember(member);
        heartRequestDto.setAttraction(attraction);
        Heart heart = heartRequestDto.toEntity();

        //좋아요
        if(!isNotAlreadyLike(member, attraction)) {
            likeRepository.save(heart);
            attractionRepository.updateLikeCount(attraction);
        }

    }

    @Transactional
    public void deleteLike(Integer attractionId , String userId) throws Exception{
        Member member = memberRepository.findByEmail(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다"));
        Attraction attraction = attractionRepository.findById(attractionId)
                .orElseThrow(() -> new Exception("해당하는 관광지를 찾을 수 없습니다."));

        Heart heart = likeRepository.findByMemberAndAttraction(member,attraction).orElseThrow(()-> new Exception("해당하는 like를 찾을 수 없음"));

        likeRepository.delete(heart);

        attractionRepository.updateDownLike(attraction);


    }

    //사용자가 이미 좋아요 한 게시물인지 체크
    private boolean isNotAlreadyLike(Member member, Attraction attraction) {
        Optional<Heart> heartOptional = likeRepository.findByMemberAndAttraction(member,attraction);
        if(heartOptional.isPresent()){
            //값이 있으면 true
            return true;
        }
        else{
            System.out.println("heartOptional = " + heartOptional);
            //빈 optional 이면 false
            return false;
        }
    }

}
