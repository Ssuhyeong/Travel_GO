package com.ssafy.trip.repository.attracion;

import com.ssafy.trip.Entity.Attraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AttractionRepositoryCustom {

    Page<Attraction> findBySearchOption(
            Pageable pageable, Integer sidoCode, Integer contentTypeId, String keyword, Integer contentId
    );

    void  updateLikeCount(Attraction attraction1);
    void  updateDownLike(Attraction attraction1);

}
