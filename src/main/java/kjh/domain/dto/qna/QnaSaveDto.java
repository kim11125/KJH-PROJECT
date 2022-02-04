package kjh.domain.dto.qna;

import kjh.domain.entity.QnaEntity;
import lombok.Data;

@Data
public class QnaSaveDto {
	
	private String category;
	private String subject;
	private String content;
	
	public QnaEntity toEntity() {
		return QnaEntity.builder()
		.category(category)
		.subject(subject)
		.content(content)
		.build();
	}
	
}
