package kjh.domain.dto.reply;

import java.time.LocalDateTime;

import kjh.domain.entity.ReplyEntity;
import lombok.Data;

@Data
public class ReplyListDto {
	
	private long rno;
	private String reply;
	private String replyer;
	private LocalDateTime createdDate;
	
	public ReplyListDto(ReplyEntity entity) {
		this.rno = entity.getRno();
		this.reply = entity.getReply();
		this.replyer = entity.getReplyer();
		this.createdDate = entity.getCreatedDate();
	}
	
	
	
}
