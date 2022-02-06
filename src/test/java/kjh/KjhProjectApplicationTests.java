package kjh;

import java.security.Principal;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import kjh.domain.entity.BoardEntity;
import kjh.domain.entity.BoardEntityRepository;
import kjh.domain.entity.MemberEntity;
import kjh.domain.entity.MemberEntityRepository;
import kjh.domain.entity.QnaEntity;
import kjh.domain.entity.QnaEntityRepository;
import kjh.domain.entity.ReplyEntity;
import kjh.domain.entity.ReplyEntityRepository;
import kjh.domain.entity.Visual;
import kjh.domain.entity.VisualRepository;
import kjh.security.MemberRole;

@SpringBootTest
class KjhProjectApplicationTests {
	
	@Autowired
	private MemberEntityRepository memRepository;
	
	@Autowired
	private BoardEntityRepository boardRepository;

	@Autowired
	private VisualRepository vrepository;
	
	@Autowired
	private ReplyEntityRepository replyRepository;
	
	@Autowired
	private QnaEntityRepository qnaRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//@Test
	void admin생성() {
		MemberEntity entity = MemberEntity.builder()
				.userId("123")
				.pw(passwordEncoder.encode("123"))
				.name("admin")
				.nick("관리자")
				.email("admin@admin.com")
				.phone("010-1234-1234")
				.address1("서울")
				.address2("중구")
				.vga("vga")
				.sn("시리얼넘버")
				.mailing("t")
				.open("f")
				.build();
		
		entity.addRole(MemberRole.USER);
		entity.addRole(MemberRole.ADMIN);
		
		memRepository.save(entity);
	}
	
	//@Test
	void 유저한명가입() {
		MemberEntity entity = MemberEntity.builder()
				.userId("444")
				.pw(passwordEncoder.encode("444"))
				.name("유저")
				.nick("회원")
				.email("user@user.com")
				.phone("010-2222-2222")
				.address1("서울시")
				.address2("종로구")
				.vga("1060")
				.sn("없음")
				.build();
		
		entity.addRole(MemberRole.USER);
		
		memRepository.save(entity);
	}
	
	
	//@Test
	void 회원탈퇴() {
		memRepository.deleteById((long) 17);
	}
	
	
	//@Test
	void 회원여러명가입테스트() {
		IntStream.rangeClosed(1, 5).forEach(i->{
			MemberEntity entity = MemberEntity.builder()
					.userId("test"+i)
					.pw(passwordEncoder.encode("123"))
					.name("user"+i)
					.nick("유저"+i)
					.email("user@user.com")
					.phone("010-1234-1234")
					.address1("서울")
					.address2("중구")
					.vga("vga")
					.sn("시리얼넘버")
					.mailing(null)
					.open(null)
					.build();
			
			entity.addRole(MemberRole.USER);
			
			memRepository.save(entity);
		});
	}
	
	//@Test
	void 자료실테스트() {
		IntStream.rangeClosed(1, 500).forEach(i->{
			BoardEntity entity = BoardEntity.builder()
					.category("공고")
					.content("내용테스트 "+i)
					.subject("제목테스트 "+i)
					.writer("최고관리자")
					.build();
			
			boardRepository.save(entity);
		});
	}
	
	
	//@Test
	void 파일테스트() {
		IntStream.rangeClosed(1, 6).forEach(i->{
			Visual entity = Visual.builder()
					.filePath("/file/visual/")
					.fileName("main-ban"+i+".jpg")
					.fileSize(0)
					.build();
			
			vrepository.save(entity);
		});
	}
	
	//@Test
	void 댓글입력테스트() {
			IntStream.rangeClosed(1, 10).forEach(i->{
				ReplyEntity entity = ReplyEntity.builder()
						.reply("댓글 "+i)
						.replyer("작성자"+i)
						.qna(QnaEntity.builder().qno(17).build())
						.build();
				
				replyRepository.save(entity);
			});
	}
	
	//@Test
	void qna입력(Principal principal) {
		QnaEntity entity = QnaEntity.builder()
				.category("회원정보").subject("테스트제목").content("테스트내용")
				.build();
		qnaRepository.save(entity);
	}
	
	
	
	
}
