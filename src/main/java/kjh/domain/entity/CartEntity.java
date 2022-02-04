package kjh.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CartEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long cno;
	@Column(nullable = false)
	private int ea;
	@Column(nullable = false)
	private long price;
	
	
	@JoinColumn(name = "gno")
	@ManyToOne
	public GoodsEntity goods;
	
	@JoinColumn(name = "mno")
	@OneToOne
	public MemberEntity member;
	
}
