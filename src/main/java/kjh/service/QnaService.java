package kjh.service;

import java.security.Principal;

import org.springframework.ui.Model;

import kjh.domain.dto.qna.QnaSaveDto;
import kjh.domain.dto.qna.QnaUpdateDto;
import kjh.domain.dto.reply.ReplyEditDto;
import kjh.domain.dto.reply.ReplySaveDto;

public interface QnaService {

	String qnaList(Model model, int page);

	String qnawrite(QnaSaveDto dto, Principal principal);

	String qnaDetail(long qno, Model model);

	String qnaUpadte(long qno, QnaUpdateDto dto);
	
	String qnaDelete(long qno);

////////////////////////////댓글////////////////////////////////////////
	String getReplies(long qno, int page, Model model);

	void replySave(long qno, ReplySaveDto dto);

	void replyEdit(long rno, ReplyEditDto dto);
	
	void replyDelete(long rno);


}
