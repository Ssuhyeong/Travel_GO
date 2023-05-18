package com.ssafy.trip.Entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String password;
	private String name;

//	@ManyToOne
//	@JoinColumn(name = "")
//	private OpenBoard openBoard;

//	private String role;
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "refresh_token")
	private String refreshToken;

	// 유저 권한 설정 메소드
	public void authorizeUser() {
		this.role = Role.USER;
	}

	// 비밀번호 암호화 메소드
	public void passwordEncode(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
	}

	public void updateRefreshToken(String updateRefreshToken) {
		this.refreshToken = updateRefreshToken;
	}


//	public void encodePassword(PasswordEncoder passwordEncoder){
//		this.password = passwordEncoder.encode(password);
//	}
//
//	@ElementCollection(fetch = FetchType.EAGER)
//	@Builder.Default
//	private List<String> roles = new ArrayList<>();
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//	}
//
//	@Override
//	public String getUsername() {
//		return name;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
}
