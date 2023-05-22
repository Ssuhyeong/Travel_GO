package com.ssafy.trip.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "schedule")
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer day;

    @ManyToOne(targetEntity = Member.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email" , referencedColumnName = "email")
    private Member member;

    @ManyToOne(targetEntity = Attraction.class ,fetch = FetchType.LAZY )
    @JoinColumn(name = "attraction_id" , referencedColumnName = "content_id")
    private Attraction attraction;
}
