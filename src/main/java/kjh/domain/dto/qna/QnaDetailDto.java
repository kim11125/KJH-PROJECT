package kjh.domain.dto.qna;

import java.time.LocalDateTime;

import kjh.domain.entity.QnaEntity;
import lombok.Data;

@Data
public class QnaDetailDto {
	
	private long qno;
	private String subject;
	private String content;
	private String writer;
	
	private LocalDateTime createdDate;
	
	public QnaDetailDto(QnaEntity entity) {
		this.qno = entity.getQno();
		this.subject = entity.getSubject();
		this.content = entity.getContent();
		this.writer = entity.getMember().getName();
		this.createdDate = entity.getCreatedDate();
	}
	
	
}
