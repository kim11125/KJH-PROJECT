package kjh.domain.dto.goods;

import java.time.LocalDateTime;

import kjh.domain.entity.GoodsEntity;
import lombok.Data;

@Data
public class GoodsListDto {

	private long gno;
	private String filePath;
	private String fileName;
	private String title;
	private String subTitle;
	private long price;
	private long discount;
	private long stock;
	
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	public GoodsListDto(GoodsEntity entity) {
		this.gno = entity.getGno();
		this.filePath = entity.getFilePath();
		this.fileName = entity.getFileName();
		this.title = entity.getTitle();
		this.subTitle = entity.getSubTitle();
		this.price = entity.getPrice();
		this.discount = entity.getDiscount();
		this.stock = entity.getStock();
		this.createdDate = entity.getCreatedDate();
		this.updatedDate = entity.getUpdatedDate();
	}
}
