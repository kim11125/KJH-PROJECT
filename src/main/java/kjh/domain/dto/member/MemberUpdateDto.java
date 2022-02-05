package kjh.domain.dto.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import kjh.domain.entity.MemberEntity;
import lombok.Data;

@Data
public class MemberUpdateDto {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private long mno;
	private String userId;
	private String pw;
	private String name;
	private String nick;
	private String email;
	private String phone;
	private String address1;
	private String address2;
	private String vga;
	private String sn;
	
	private String mailing;
	private String open;
	
	public MemberEntity update() {
		return MemberEntity.builder()
				.mno(mno)
				.userId(userId).pw(pw).name(name).nick(nick).email(email).phone(phone)
				.address1(address1).address2(address2)
				.vga(vga).sn(sn)
				.mailing(mailing).open(open)
				.build();
	}
	
	
}
