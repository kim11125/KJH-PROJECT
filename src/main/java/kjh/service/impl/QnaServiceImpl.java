package kjh.service.impl;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kjh.domain.dto.qna.QnaDetailDto;
import kjh.domain.dto.qna.QnaListDto;
import kjh.domain.dto.qna.QnaSaveDto;
import kjh.domain.dto.qna.QnaUpdateDto;
import kjh.domain.dto.reply.ReplyEditDto;
import kjh.domain.dto.reply.ReplyListDto;
import kjh.domain.dto.reply.ReplySaveDto;
import kjh.domain.dto.util.PageDto;
import kjh.domain.entity.MemberEntityRepository;
import kjh.domain.entity.QnaEntity;
import kjh.domain.entity.QnaEntityRepository;
import kjh.domain.entity.ReplyEntity;
import kjh.domain.entity.ReplyEntityRepository;
import kjh.service.QnaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private QnaEntityRepository repository;
	
	@Autowired
	private MemberEntityRepository memberEntityRepository;
	
	//qnalist 페이징처리 완료
	@Override
	public String qnaList(Model model, int page) {
		
		int size = 10; //한페이지에 표현할 게시글 수
		Sort sort = Sort.by(Direction.DESC, "qno"); //Qnaentity의 qno기준으로 내림차순정렬
		
		Pageable pageable = PageRequest.of(page-1, size, sort);
		
		//repo에서 pageable 정렬기준으로 찾음
		Page<QnaEntity> result = repository.findAll(pageable);
		
		//페이지 정보를 "page" 객체에 담음
		//한 블럭에 표현할 페이지 갯수
		model.addAttribute("page", new PageDto(3, page, result.getTotalPages()));
		
		//QnaListDto 객체 정보 찾아서 "list" 변수에 저장
		List<QnaListDto> list = repository.findAll(pageable).stream()
									.map(QnaListDto::new)
									.collect(Collectors.toList());
		
		//위에서 list변수 값을 html로 뿌리기 위해 model로 "list"에 담음
		model.addAttribute("list", list);
		
		return "/custom/qna/qnaList";
	}


	//qna글쓰기 처리 로그인시 유저정보 저장된 이름값 불러옴
	@Override
	public String qnawrite(QnaSaveDto dto, Principal principal) {
	
		QnaEntity entity = QnaEntity.builder()
				.category(dto.getCategory())
				.subject(dto.getSubject())
				.content(dto.getContent())
				.member(memberEntityRepository.findByUserId(principal.getName()).get() )
				//MemberEntityRepository에 저장된 아이디에서 principal.getName()해서 찾아옴
				.build();
		repository.save(entity);
		return "redirect:/qna";
	}


	//qna디테일
	@Override
	public String qnaDetail(long qno, Model model) {
		QnaDetailDto result = repository.findById(qno)
										.map(QnaDetailDto::new)
										.orElseThrow();
		model.addAttribute("detail", result);
		return "/custom/qna/qnaDetail";
	}
	
	//qna 수정
	@Transactional
	@Override
	public String qnaUpadte(long qno, QnaUpdateDto dto) {
		repository.findById(qno).map(e->e.qnaUpdate(dto));
		return "redirect:/qna/{qno}";
	}

	

	//qna 삭제
	@Override
	public String qnaDelete(long qno) {
		repository.deleteById(qno);
		return "redirect:/qna";
	}
	
	
//////////////////////////////////댓글////////////////////////////////////////////
	
	private final ReplyEntityRepository replyRepository;
	
	
	@Override
	public String getReplies(long qno, Model model) {
//		Sort sort = Sort.by(Direction.DESC, "rno");
		
		
		//한페이지에 표현할 댓글 수
//		Pageable pageable = PageRequest.of(page - 1 , 5, sort);
//
//		Page<ReplyEntity> reply = replyRepository.findAll(pageable);
//		
//		//한 화면에 표현할 댓글페이지 갯수, 시작페이지, 총페이지
//		model.addAttribute("rpage", new PageDto(5, page, reply.getTotalPages()));
		
		List<ReplyListDto> result = replyRepository.findAllByQna_qno(qno).stream()
										.map(ReplyListDto::new)
										.collect(Collectors.toList());
		model.addAttribute("reply", result); 
		return "/custom/qna/replies";
	}


	//댓글저장처리	
	@Override
	public void replySave(long qno, ReplySaveDto dto) {
		ReplyEntity entity = ReplyEntity.builder()
				.reply(dto.getReply()).replyer(dto.getReplyer())
				.qna(QnaEntity.builder().qno(qno).build())
				.build();
		replyRepository.save(entity);
	}
	
	//댓글 수정
	@Transactional
	@Override
	public void replyEdit(long rno, ReplyEditDto dto) {
		replyRepository.findById(rno).map(e->e.replyEdit(dto)).orElseThrow();
	}


	//댓글삭제처리
	@Override
	public void replyDelete(long rno) {
		replyRepository.deleteById(rno);
	}


	
}
