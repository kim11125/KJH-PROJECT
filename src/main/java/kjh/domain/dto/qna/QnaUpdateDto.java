package kjh.domain.dto.qna;

import kjh.domain.entity.QnaEntity;
import lombok.Data;

@Data
public class QnaUpdateDto {
	
	private String category;
	private String subject;
	private String content;
	
	public QnaEntity qnaUpdate() {
		return QnaEntity.builder()
				.category(category).subject(subject).content(content)
				.build();
	}
	
}
