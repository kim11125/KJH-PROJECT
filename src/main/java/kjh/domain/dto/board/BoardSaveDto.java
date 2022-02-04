package kjh.domain.dto.board;

import kjh.domain.entity.BoardEntity;
import lombok.Data;

@Data 
public class BoardSaveDto {
	
	private String category;
	private String subject;
	private String content;
	private String writer;
	
	
	//save를 위한
	public BoardEntity toEntity() {
		return BoardEntity.builder()
				.category(category).subject(subject).content(content).writer(writer)
				.build();
	}
		
}
