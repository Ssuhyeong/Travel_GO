package com.ssafy.trip.Entity;

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
@Table(name = "travel_routes")
@Entity
public class TravelRoutes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Member.class,fetch = FetchType.LAZY )
    @JoinColumn(name = "member_email" , referencedColumnName = "email")
    private Member member;

    @Column(name = "schedule_info")
    private Integer scheduleInfo;
    private String title;

    public TravelRoutes (Integer scheduleInfo){
        this.scheduleInfo = scheduleInfo;
    }

}
