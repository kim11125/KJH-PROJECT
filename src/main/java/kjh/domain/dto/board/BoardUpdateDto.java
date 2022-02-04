package kjh.domain.dto.board;

import java.time.LocalDateTime;

import kjh.domain.entity.BoardEntity;
import lombok.Data;

@Data
public class BoardUpdateDto {

	private String category;
	private String subject;
	private String content;
	private String writer;
	
	private LocalDateTime updatedDate;
	
	public BoardEntity update() {
		return BoardEntity.builder()
				.category(category).subject(subject).content(content).writer(writer)
				.build();
	}
		
}
