package com.ssafy.trip.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Table(name = "review")
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;
    private Integer star;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "attraction_id")
    private Attraction attraction;


    public void setReview (Attraction attraction){
        this.attraction = attraction;
        attraction.getReviews().add(this);
    }

}
