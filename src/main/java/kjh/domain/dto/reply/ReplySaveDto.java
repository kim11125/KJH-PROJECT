package kjh.domain.dto.reply;

import kjh.domain.entity.QnaEntity;
import kjh.domain.entity.ReplyEntity;
import lombok.Data;

@Data
public class ReplySaveDto {

	private String reply;
	private String replyer;
	
	public ReplyEntity toEntity(long qno) {
		return ReplyEntity.builder()
				.reply(reply).replyer(replyer)
				.qna(QnaEntity.builder().qno(qno).build())
				.build();
	}
	
}
