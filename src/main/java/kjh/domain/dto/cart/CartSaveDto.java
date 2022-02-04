package kjh.domain.dto.cart;

import lombok.Data;

@Data
public class CartSaveDto {
	
	private int ea;
	private long price;
	
	// goods에서 가져올거
	private long gno;
	private String title;
	private String subTitle;
	private long discount;
	
	private String filePath;
	private String fileName;
	
	//
}
