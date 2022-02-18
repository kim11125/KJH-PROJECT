package kjh.service.impl;

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

import kjh.domain.dto.board.BoardDetailDto;
import kjh.domain.dto.board.BoardListDto;
import kjh.domain.dto.board.BoardSaveDto;
import kjh.domain.dto.board.BoardUpdateDto;
import kjh.domain.dto.util.PageDto;
import kjh.domain.entity.BoardEntity;
import kjh.domain.entity.BoardEntityRepository;
import kjh.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardEntityRepository repository;
	
	//자료실 페이지 이동 및 페이징처리 완료
	@Override
	public String BoardPage(Model model, int page) {
		
		int size = 15; //한페이지에 표현할 게시글 수
		
		Sort sort = Sort.by(Direction.DESC, "bno"); //board entity의 bno기준으로 내림차순정렬
		
		Pageable pageable = PageRequest.of(page-1, size, sort);
		
		//repo에서 pageable 정렬기준으로 찾음
		Page<BoardEntity> result = repository.findAll(pageable);
		
		//페이지 정보를 "page" 객체에 담음
		//한 블럭에 표현할 페이지 갯수
		model.addAttribute("page", new PageDto(10, page, result.getTotalPages()));
		
		//BoardListDto 객체 정보 찾아서 "list" 변수에 저장
		List<BoardListDto> list = result.getContent().stream()
									.map(BoardListDto::new)
									.collect(Collectors.toList());
		
		//위에서 list변수 값을 html로 뿌리기 위해 model로 "list"에 담음
		
		model.addAttribute("list", list);
		
		
		
		return "/custom/board/boardList";
	}
	
	//글쓰기처리
	@Override
	public String write(BoardSaveDto dto) {
		repository.save(dto.toEntity());
		return "redirect:/custom/board";
	}

	//디테일처리
	@Transactional
	@Override
	public String detail(long bno, Model model) {
		BoardDetailDto result = repository.findById(bno) // repo에서 bno값으로 해당하는 객체 찾아서
									.map(e->e.readCountUp()) //조회수 1업
									.map(BoardDetailDto :: new) //dto에서 매핑한거 참조값으로 가져옴
									.orElseThrow(); //값이 없으면 Exception 던짐
		//디테일 html에 값을 뿌리기 위한 변수 detail에 위의 값 저장
		model.addAttribute("detail", result);
		
		return "/custom/board/boardDetail";
	}

	@Transactional //수정처리 하려면 필요함(커밋)
	@Override
	public String update(long bno, BoardUpdateDto dto) {
		repository.findById(bno).map(e->e.update(dto));
		return "redirect:/custom/board/{bno}";
	}

	//게시글 삭제 처리
	@Override
	public String delete(long bno) {
		repository.deleteById(bno); // repo에서 bno로 해당하는 객체 찾아서 삭제
		return "redirect:/custom/board"; //삭제하면 자료실 리스트페이지로 재요청
	}

}
