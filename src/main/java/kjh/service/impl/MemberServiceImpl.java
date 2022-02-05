package kjh.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	//회원가입처리
	@Override
	public String register(MemberSaveDto dto) {
		
		/*
		MemberEntity entity = MemberEntity.builder()
				.userId(dto.getUserId())
				.pw(passwordEncoder.encode(dto.getPw()))
				.name(dto.getName())
				.nick(dto.getNick())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.address1(dto.getAddress1())
				.address2(dto.getAddress2())
				.vga(dto.getVga())
				.sn(dto.getSn())
				.mailing(dto.getMailing())
				.open(dto.getOpen())
				.build();
		
		entity.addRole(MemberRole.USER); //기본으로 유저롤 부여
		 */
		
		MemberEntity entity;
		
		dto.setPw(passwordEncoder.encode(dto.getPw()));
		
		entity = dto.toEntity();
		
		entity.addRole(MemberRole.USER); //기본으로 유저롤 부여
		
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
	
//	//회원탈퇴처리
//	@Transactional
//	@Override
//	public void withdraw(long mno) {
//		repository.deleteById(mno);
//	}


}
