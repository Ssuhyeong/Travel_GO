package com.ssafy.trip.Entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "faq")
@Data
@Entity
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String title;
    private String content;
    private int hit;
//
//    @OneToMany(mappedBy = "user")
//    private List<User> user = new ArrayList<>();

    @Column(name = "user_id")
    private String userId;

    @Column(name = "register_time")
    private Timestamp registerTime;

}
