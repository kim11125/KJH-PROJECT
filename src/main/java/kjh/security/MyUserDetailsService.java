package kjh.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kjh.domain.entity.MemberEntity;
import kjh.domain.entity.MemberEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	MemberEntityRepository repository;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username = " + username);
		log.info("이메일테스트 email = " + username);
		
		Optional<MemberEntity> result = repository.findByUserId(username);
		//회원 존재 유무 확인
		if(result.isEmpty()) { //회원이 존재하지않는 경우
			throw new UsernameNotFoundException("회원이 존재하지 않습니다");
		}
		
		//아이디가 존재하는 경우
		MemberEntity entity = result.get();
		//UserDetails : id(username), pw(password), role
		
		//인증정보는 UserDetails 타입으로 리턴해야함
		return new MyUserDetails(entity);
	}


}
