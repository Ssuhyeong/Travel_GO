package com.ssafy.trip.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "heart")
public class Heart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Attraction.class ,fetch = FetchType.LAZY )
    @JoinColumn(name = "attraction_id" , referencedColumnName = "content_id")
    private Attraction attraction;

    @ManyToOne(targetEntity = Member.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email" , referencedColumnName = "email")
    private Member member;

    public Heart(Attraction attraction, Member member) {
        this.attraction = attraction;
        this.member = member;
    }
}
