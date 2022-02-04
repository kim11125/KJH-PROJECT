package kjh.domain.dto.board;

import kjh.domain.entity.BaseEntity;
import kjh.domain.entity.BoardEntity;
import lombok.Getter;

@Getter
public class BoardListDto extends BaseEntity{

	private long bno;
	private String category;
	private String subject;
	private String writer;
	private int readCount;
	
	public BoardListDto(BoardEntity entity) {
		this.bno = entity.getBno();
		this.category = entity.getCategory();
		this.subject = entity.getSubject();
		this.writer = entity.getWriter();
		this.readCount = entity.getReadCount();
		
		createdDate = entity.getCreatedDate();
		updatedDate = entity.getUpdatedDate();
	}
	
	
	
	
	
}
