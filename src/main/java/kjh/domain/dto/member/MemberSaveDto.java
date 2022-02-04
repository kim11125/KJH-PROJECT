package kjh.domain.dto.member;

import lombok.Data;

@Data
public class MemberSaveDto{
	
	
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
	
	/*
	public MemberEntity toEntity() {
		return MemberEntity.builder()
				.userId(id)
				.pw(pw)
				.name(name)
				.nick(nick)
				.email(email)
				.phone(phone)
				.address1(address1)
				.address2(address2)
				.vga(vga)
				.sn(sn)
				.mailing(mailing)
				.open(open)
				.build();
		
	}
	*/
}
