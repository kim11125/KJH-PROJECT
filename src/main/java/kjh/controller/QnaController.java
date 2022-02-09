package kjh.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kjh.domain.dto.qna.QnaSaveDto;
import kjh.domain.dto.qna.QnaUpdateDto;
import kjh.domain.dto.reply.ReplyEditDto;
import kjh.domain.dto.reply.ReplySaveDto;
import kjh.service.QnaService;

@Controller
public class QnaController {
	
	@Autowired
	private QnaService service;
	
	
	//qna페이지 이동
	@GetMapping("/qna")
	public String qnalist(Model model, @RequestParam(defaultValue = "1") int page) {
		return service.qnaList(model, page);
	}
	
	//qna작성페이지 이동
	@GetMapping("/qna/write")
	public String qnaWrite(){
		return "/custom/qna/qnaWirte";
	}
	
	//qna작성처리
	@PostMapping("/qna/write")
	public String qnaWrite(QnaSaveDto dto, Principal principal) {
		System.out.println(principal.getName());
		return service.qnawrite(dto, principal);
	}
	
	//qna상세페이지 이동
	@GetMapping("/qna/{qno}")
	public String qnaDetail(@PathVariable long qno, Model model) {
		return service.qnaDetail(qno, model);
	}
	
	//qna 수정처리
	@PutMapping("/qna/{qno}")
	public String qnaUpdate(@PathVariable long qno, QnaUpdateDto dto) {
		return service.qnaUpadte(qno, dto);
	}
	
	
	//qna삭제처리
	@DeleteMapping("/qna/{qno}")
	public String qnaDelete(@PathVariable long qno) {
		return service.qnaDelete(qno);
	}
	
//////////////////////////////댓글처리///////////////////////////////////////////
	
	//댓글 읽어오기
	@GetMapping("/qna/{qno}/replies")
	public String getReplies(@PathVariable long qno, Model model) {
		return service.getReplies(qno, model);
	}
	
	//댓글 저장처리
	@ResponseBody
	@PostMapping("/qna/{qno}/replies")
	public void replySave(@PathVariable long qno, ReplySaveDto dto) {
		service.replySave(qno, dto);
	}
	
	//댓글 수정처리
	@ResponseBody
	@PutMapping("/qna/{qno}/replies/{rno}")
	public void replyEdit(@PathVariable long rno, ReplyEditDto dto) {
		service.replyEdit(rno, dto);
	}
	
	
	//댓글 삭제처리
	@ResponseBody
	@DeleteMapping("/replies/{rno}")
	public void replyDelete(@PathVariable long rno) {
		service.replyDelete(rno);
	}
	
	
	
}
