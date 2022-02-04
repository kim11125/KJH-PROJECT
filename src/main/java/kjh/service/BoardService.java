package kjh.service;

import org.springframework.ui.Model;

import kjh.domain.dto.board.BoardSaveDto;
import kjh.domain.dto.board.BoardUpdateDto;

public interface BoardService {
	
	//게시판 단순이동처리
	String BoardPage(Model model, int page);
	
	//게시판 글쓰기 처리(파일은 아직 미완성)
	String write(BoardSaveDto dto);

	//디테일페이지 이동
	String detail(long bno, Model model);
	
	//게시글 수정처리
	String update(long bno, BoardUpdateDto dto);
	
	//게시글 삭제
	String delete(long bno);
	
//////////////////////////////////////////////////////////////


}
