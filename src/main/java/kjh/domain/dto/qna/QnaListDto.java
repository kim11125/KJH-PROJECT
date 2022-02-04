package kjh.domain.dto.qna;

import java.time.LocalDateTime;

import kjh.domain.entity.QnaEntity;
import lombok.Data;

@Data
public class QnaListDto {
	
	private long qno;
	private String category;
	private String subject;
	private String writer;
	private LocalDateTime createdDate;
	
	public QnaListDto(QnaEntity entity) {
		this.qno = entity.getQno();
		this.category = entity.getCategory();
		this.subject = entity.getSubject();
		this.createdDate = entity.getCreatedDate();
		this.writer = entity.getMember().getName();
	}
	
	
}
