package com.ssafy.trip.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

@NoArgsConstructor
@Data
@Table(name = "review")
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_no")
    private Integer articleNo;

    private String subject;
    private String content;
    private Integer star;

    @ManyToOne(targetEntity = Attraction.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "attraction_id" , referencedColumnName = "content_id")
    private Attraction attraction;

    @ManyToOne(targetEntity = Member.class , fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email",referencedColumnName = "email")
    private Member member;

    @Column(name = "register_time")
    private String registerTime;

    @PrePersist
    void registerTime() {
        Timestamp time = Timestamp.from(Instant.now());
        this.registerTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(time);
    }

    public void setReview (Attraction attraction){
        this.attraction = attraction;
        attraction.getReviews().add(this);
    }

}
