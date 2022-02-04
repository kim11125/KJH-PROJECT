package kjh.domain.dto.board;

import java.time.LocalDateTime;

import kjh.domain.entity.BoardEntity;
import lombok.Data;

@Data
public class BoardDetailDto {
	
	private long bno;
	private String category;
	private String subject;
	private String content;
	private String writer;
	
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	//BoardEntity 객체의 필드값을 BoardDetailDto 필드로 매핑
	public BoardDetailDto(BoardEntity entity) {
		this.bno = entity.getBno();
		this.category = entity.getCategory();
		this.subject = entity.getSubject();
		this.content = entity.getContent();
		this.writer = entity.getWriter();
		this.createdDate = entity.getCreatedDate();
		this.updatedDate = entity.getUpdatedDate();
	}
	
	
	
}
