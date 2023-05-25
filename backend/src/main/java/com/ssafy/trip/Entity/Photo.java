package com.ssafy.trip.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Setter
@Entity(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_name",nullable = false)
    private String originalName;

    @Column(name = "file_path",nullable = false)
    private String filePath;

    @JsonIgnoreProperties({"member"})
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_email",referencedColumnName = "email")
    private Member member;

    @Column(name = "file_size")
    private Long fileSize;

    @Builder
    public Photo(String originalName, String filePath, Long fileSize) {
        this.originalName = originalName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void setMember(Member member){
        if(this.member != null){
            this.member.getPhotos().remove(this);
        }
        this.member = member;

        if(!member.getPhotos().contains(this)){
            member.getPhotos().add(this);
        }
    }

}
