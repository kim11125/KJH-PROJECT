package kjh.security;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kjh.domain.entity.MemberEntity;
import kjh.domain.entity.MemberEntityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService{
	
	private final PasswordEncoder passwordEncoder;
	private final MemberEntityRepository memberEntityRepository;
	
	private long mno;
	
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		//소셜로그인 인증완료
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		//*
		Map<String, Object> map = oauth2User.getAttributes();
		
		for(String key : map.keySet()) {
			System.out.println( key + " = " + map.get(key) );
		}
		//*/
		//DefaultOAuth2User	: 소셜유저
		//return oauth2User;
		return saveSocialUser(registrationId, oauth2User);
	}

	private OAuth2User saveSocialUser(String registrationId, OAuth2User oauth2User) {
		String email = null;
		String name = null;
		
		if(registrationId.equals("google")) {
			email = oauth2User.getAttribute("email");
			name = oauth2User.getAttribute("name");
		}else if(registrationId.equals("naver")) {
			//회원정보를 repsponse라는 name으로 json data로 제공
			Map<String, Object> result = oauth2User.getAttribute("response");
			for(String key : result.keySet()) {
				System.out.println(key + ":" + result.get(key));
			}
			
			email = (String) result.get("email");
			name = (String) result.get("name");
		}
		
		//가입여부 체크
		Optional<MyUserDetails> check = memberEntityRepository.findById(mno)
				.map(MyUserDetails::new);
		if(check.isPresent()) {
			return check.get();
		}
		
		
		//가입이 안되어있으면 소셜정보 회원가입처리
		MemberEntity entity = MemberEntity.builder()
								.email(email).name(name).isSocial(true)
								.pw(passwordEncoder.encode("social"+System.currentTimeMillis()))
								.build();
		entity.addRole(MemberRole.USER);
		//MyUserDetails		: 일반유저
		
		MemberEntity result = memberEntityRepository.save(entity);
		MyUserDetails myUserDetails = new MyUserDetails(result);
		
		return myUserDetails;
	}


}
