package com.ssafy.trip.repository.attracion;

import com.ssafy.trip.Entity.Attraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttractionRepositoryCustom {

    Page<Attraction> findBySearchOption(
            Pageable pageable, Integer sidoCode, Integer contentTypeId, String keyword, Integer contentId
    );

    Page<Attraction> findBySearchOrderBy(Pageable pageable,String keyword,Integer sidoCode, Integer contentTypeId);

    void  updateLikeCount(Attraction attraction1);
    void  updateDownLike(Attraction attraction1);

    List<Attraction> findByBestLike();

    List<Attraction> findByKeyword(String keyword);

}
