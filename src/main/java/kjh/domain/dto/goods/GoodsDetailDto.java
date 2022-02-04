package kjh.domain.dto.goods;

import kjh.domain.entity.GoodsEntity;
import lombok.Data;

@Data
public class GoodsDetailDto {

	private String filePath;
	private String fileName;
	private String title;
	private String subTitle;
	private String goodsDetail;
	private long price;
	private long discount;
	private long stock;
	
	
	public GoodsDetailDto(GoodsEntity entity) {
		this.filePath = entity.getFilePath();
		this.fileName = entity.getFileName();
		this.title = entity.getTitle();
		this.subTitle = entity.getSubTitle();
		this.goodsDetail = entity.getGoodsDetail();
		this.price = entity.getPrice();
		this.discount = entity.getDiscount();
		this.stock = entity.getStock();
	}
	
	
	
}
