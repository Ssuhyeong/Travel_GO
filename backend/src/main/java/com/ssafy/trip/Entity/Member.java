package com.ssafy.trip.Entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "member")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String password;
	private String name;
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "refresh_token")
	private String refreshToken;


	// 유저 권한 설정 메소드
	public void authorizeUser() {
		this.role = Role.USER;
	}

	public Member(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	// 비밀번호 암호화 메소드
	public void passwordEncode(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
	}

	public void updateRefreshToken(String updateRefreshToken) {
		this.refreshToken = updateRefreshToken;
	}


}
