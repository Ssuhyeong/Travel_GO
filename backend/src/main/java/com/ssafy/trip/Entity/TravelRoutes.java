package com.ssafy.trip.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "travel_routes")
@Entity
public class TravelRoutes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Member.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email" , referencedColumnName = "email")
    private Member member;

//    @ManyToOne(targetEntity = Schedule.class,fetch = FetchType.EAGER)
//    @JoinColumn(name = "schedule_info" , referencedColumnName = "schedule_num")
//    private Schedule schedule;


    @Column(name = "schedule_info")
    private Integer scheduleInfo;
    private String title;

}
