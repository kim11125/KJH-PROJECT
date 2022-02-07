package kjh.domain.dto.mybatis;

import java.time.LocalDateTime;

import kjh.domain.entity.BaseEntity;
import lombok.Data;

@Data
public class FaqDto{

	private long fno;
	private String subject;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
}
