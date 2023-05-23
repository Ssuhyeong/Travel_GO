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
@Table(name = "schedule")
@Entity
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer day;

    @Column(name = "schedule_num")
    private Integer scheduleNum;

    @ManyToOne(targetEntity = Member.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email" , referencedColumnName = "email")
    private Member member;

    @ManyToOne(targetEntity = Attraction.class ,fetch = FetchType.EAGER )
    @JoinColumn(name = "attraction_id" , referencedColumnName = "content_id")
    private Attraction attraction;
}
