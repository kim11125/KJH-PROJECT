package kjh.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GoodsEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long gno;
	@Column(nullable = false)
	private String category;
	@Column(nullable = false)
	private String title;

	private String subTitle;
	
	@Column(nullable = false)
	private String goodsDetail;
	@Column(nullable = false)
	private long price;
	@Column(nullable = false)
	private long stock;
	
	//할인여부는 선택사항
	private long discount;
	
	//상품이미지 띄우기
	@Column(nullable = false)
	private String filePath;
	@Column(nullable = false)
	private String fileName;
	
	
}
