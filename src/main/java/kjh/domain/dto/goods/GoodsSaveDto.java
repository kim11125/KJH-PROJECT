package kjh.domain.dto.goods;

import lombok.Data;

@Data
public class GoodsSaveDto {

	private String category;
	private String title;
	private String subTitle;
	private String goodsDetail;
	private long price;
	private long stock;
	
	//할인여부는 선택사항
	private long discount;

	
	
}
