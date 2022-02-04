package kjh.security;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import kjh.domain.entity.MemberEntity;
import lombok.Data;

@Data
public class MyUserDetails extends User implements OAuth2User {
	

	//principal로 접근가능한 필드
	private String name;
	private long mno;
	//private String nick;
	private boolean isSocial;
	
	private Map<String, Object> attributes;

	
//	username = id
	public MyUserDetails(MemberEntity entity) {
		super(	
				//entity.getName(),
				entity.getUserId(),				//username
				//entity.getEmail(),			//username
				entity.getPw(),				//password
				entity.getRoleSet().stream()//role
								.map(role -> new SimpleGrantedAuthority(role.getRole()))
								.collect(Collectors.toSet()));
		//role은 Set 컬렉션의 구성으로 GrantedAuthority 타입을 요구함
		//Set<MemberRole> -> Set<SimpleGrantedAuthority>
		
		//GrantedAuthority <- SimpleGrantedAuthority
		
		name=entity.getName();
		mno = entity.getMno();
		
		isSocial = entity.isSocial();
		
	}
	
	
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}


}
