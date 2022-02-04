package kjh.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
	
	USER("ROLE_USER", "회원"),
	ADMIN("ROLE_ADMIN", "관리자");
	
	final String role; //final : 수정불가, 초기화해야함(@RequiredArgsConstructor추가 하거나)
	final String title;
	
//	@RequiredArgsConstructor 과 동일
//	private MemberRole(String role, String title) {
//		this.role = role;
//		this.title = title;
//	}
	
}
