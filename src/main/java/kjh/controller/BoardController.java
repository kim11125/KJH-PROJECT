package kjh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kjh.domain.dto.board.BoardSaveDto;
import kjh.domain.dto.board.BoardUpdateDto;
import kjh.service.BoardService;

@Controller
public class BoardController {

	/**
	 *  Board페이지로 단순 이동을 제외한 글쓰기, 수정, 삭제는 ADMIN만 가능
	 *  권한 구분을 위해 
	 *  boards 매핑된 페이지는 관리자만 접근가능
	 *  board 매핑된 페이지는 누구나 접근가능
	 */
	
	@Autowired
	private BoardService service;
	
	//자료실 페이지로 이동
	@GetMapping("/custom/board")
	public String BoardPage(Model model, @RequestParam(defaultValue = "1") int page) {
		return service.BoardPage(model, page);
	}
	
	//자료실 글쓰기페이지로 이동
	//@PreAuthorize("hasRole('USER')") //security대신 controller에서 접근권한 제어 가능
	@GetMapping("/custom/boards/write")
	public String write() {
		return "/custom/board/boardWrite";
	}
	
	//자료실 글쓰기 처리
	@PostMapping("/custom/board")
	public String write(BoardSaveDto dto){
		return service.write(dto);
	}
	
	//자료실 디테일 페이지 이동
	//no로 게시글 읽어와야해서 Query String parameter 형식으로 주소 요청
	@GetMapping("/custom/board/{bno}")
	public String detail(@PathVariable long bno, Model model) {
		//no값으로 검색해서 해당하는 게시글을 가져와야 하기 때문에 파라미터에 bno넣음
		return service.detail(bno, model);
	}
	
	//게시글 수정
	@PutMapping("/custom/board/{bno}")
	public String update(@PathVariable long bno, BoardUpdateDto dto) {
		return service.update(bno, dto);
	}
	
	//게시글 삭제처리
	@DeleteMapping("/custom/board/{bno}")
	public String delete(@PathVariable long bno) {
		return service.delete(bno);
	}
	
}
