package com.ssafy.trip.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "schedule")
@Entity
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "schedule_num")
//    private Integer scheduleNum;
    private Integer day;

    @ManyToOne(targetEntity = TravelRoutes.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_num" , referencedColumnName = "schedule_info")
    private TravelRoutes travelRoutes;
//    private TravelRoutes travelRoutes;

    @ManyToOne(targetEntity = Member.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email" , referencedColumnName = "email")
    private Member member;


    @ManyToOne(targetEntity = Attraction.class ,fetch = FetchType.EAGER )
    @JoinColumn(name = "attraction_id" , referencedColumnName = "content_id")
    private Attraction attraction;
}
