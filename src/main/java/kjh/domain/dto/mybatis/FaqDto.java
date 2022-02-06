package kjh.domain.dto.mybatis;

import kjh.domain.entity.BaseEntity;
import lombok.Data;

@Data
public class FaqDto extends BaseEntity {

	private long fno;
	private String subject;
	private String content;
}
