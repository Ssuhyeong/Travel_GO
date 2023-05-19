package com.ssafy.trip.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
@Builder
@Entity
@Data
public class Board implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_no")
	private Integer articleNo;

	private String subject;
	private String content;
	@ColumnDefault(value = "0")
	private Integer hit;

	@ManyToOne(targetEntity = Member.class , fetch = FetchType.LAZY)
	@JoinColumn(name = "member_email",referencedColumnName = "email")
	private Member member;

	@Column(name = "register_time" )
	private String registerTime;

	@PrePersist
	void registerTime() {
		Timestamp time = Timestamp.from(Instant.now());
		this.registerTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(time);
	}

}
