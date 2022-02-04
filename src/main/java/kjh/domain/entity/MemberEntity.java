package kjh.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import kjh.domain.dto.member.MemberUpdateDto;
import kjh.security.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberEntity extends BaseEntity{
	
	@Id	//pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mno;
	@Column(nullable = false,unique = true)
	private String userId;
	@Column(nullable = false)
	private String pw;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String nick;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String address1;
	@Column(nullable = false)
	private String address2;
	@Column(nullable = false)
	private String vga;
	@Column(nullable = false)
	private String sn;
	
	@Column(nullable = true)
	private String mailing;
	@Column(nullable = true)
	private String open;
	
	private boolean isSocial; //소셜유저 체크
	private String userIp;
	
	@Enumerated(EnumType.STRING) //DB에 저장시 문자열로 저장
	@ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	private Set<MemberRole> roleSet = new HashSet<>();
	
	public void addRole(MemberRole role) {
		roleSet.add(role);
	}
	
	
	public MemberEntity update(MemberUpdateDto dto) {
		mno = dto.getMno();
		userId = dto.getUserId();
		pw = dto.getPw();
		name = dto.getName();
		nick = dto.getNick();
		email = dto.getEmail();
		phone = dto.getPhone();
		address1 = dto.getAddress1();
		address2 = dto.getAddress2();
		vga = dto.getVga();
		sn = dto.getSn();
		mailing = dto.getMailing();
		open = dto.getOpen();
		return this;
	}
	
	
	
	
}
