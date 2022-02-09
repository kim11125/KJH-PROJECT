package kjh.service;

import org.springframework.ui.Model;

import kjh.domain.dto.member.MemberSaveDto;
import kjh.domain.dto.member.MemberUpdateDto;
import kjh.security.MyUserDetails;

public interface MemberService {

	String register(MemberSaveDto dto);

	String userInfo(MyUserDetails user, Model model);

	void UserInfoUpdate(MemberUpdateDto dto);

	String withdraw(MyUserDetails user, Model model);

	void withdraw(long mno);

	String idchk(String userId);

}
