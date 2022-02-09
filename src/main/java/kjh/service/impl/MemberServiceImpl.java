package kjh.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kjh.domain.dto.member.MemberSaveDto;
import kjh.domain.dto.member.MemberUpdateDto;
import kjh.domain.entity.MemberEntity;
import kjh.domain.entity.MemberEntityRepository;
import kjh.security.MemberRole;
import kjh.security.MyUserDetails;
import kjh.service.MemberService;


@Service
public class MemberServiceImpl implements MemberService {

	
	@Autowired
	private MemberEntityRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//아이디 중복체크
	@Override
	public String idchk(String userId) {
		
		String errmsg = "동일한 아이디로 가입된 계정이 있습니다.";
		//멤버엔티티에서 
		Optional<MemberEntity> result =	repository.findByUserId(userId);
		
		if(result.isEmpty()) {
			return null;
		}
		return errmsg;
	}

	//회원가입처리
	@Override
	public String register(MemberSaveDto dto) {
		
		MemberEntity entity;
		
		dto.setPw(passwordEncoder.encode(dto.getPw()));
		
		entity = dto.toEntity();
		
		if( dto.getEmail().contains("@admin.com") ) {
			//이메일이 @admin.com으로 끝나는 경우 관리자권한 부여
			entity.addRole(MemberRole.ADMIN);
		}else {
			//그외는 유저롤 부여
			entity.addRole(MemberRole.USER);
		}
		
		repository.save(entity);
		return "/member/login";
	}
	
	//회원정보 받아오기
	@Override
	public String userInfo(MyUserDetails user, Model model) {
		
		MemberEntity result = repository.findById( user.getMno() ).get();
		model.addAttribute("user", result);
		
		return "/member/userInfo";
	}
	
	//회원정보수정처리
	@Transactional
	@Override
	public void UserInfoUpdate(MemberUpdateDto dto) {
		dto.setPw(passwordEncoder.encode(dto.getPw()));
		repository.findById(dto.getMno()).map(e->e.update(dto));
	}
	
	//회원탈퇴처리
	@Override
	public String withdraw(MyUserDetails user, Model model) {
		MemberEntity result = repository.findById(user.getMno()).get();
		model.addAttribute("user", result);
		return "/member/withdraw";
	}
	
	//회원탈퇴처리
	@Override
	public void withdraw(long mno) {
		repository.deleteById(mno);
		SecurityContextHolder.clearContext();
	}

}
