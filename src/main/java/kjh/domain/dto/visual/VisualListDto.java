package kjh.domain.dto.visual;

import java.time.LocalDateTime;

import kjh.domain.entity.Visual;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class VisualListDto{

	private long vno;
	private String filePath;
	private String fileName;
	private long fileSize;
	private String title;
	private String subTitle;
	private int orderNo;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	public VisualListDto(Visual entity) {
		this.vno = entity.getVno();
		this.filePath = entity.getFilePath();
		this.fileName = entity.getFileName();
		this.fileSize = entity.getFileSize();
		this.title = entity.getTitle();
		this.subTitle = entity.getSubTitle();
		this.orderNo = entity.getOrderNo();
		this.createdDate = entity.getCreatedDate();
		this.updatedDate = entity.getUpdatedDate();
	}
	
	
}
