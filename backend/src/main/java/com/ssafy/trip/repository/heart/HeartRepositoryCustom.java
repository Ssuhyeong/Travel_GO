package com.ssafy.trip.repository.heart;

import java.util.List;

public interface HeartRepositoryCustom {

    List<Integer> findBySearch(String userId);

}
