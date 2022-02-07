package kjh.domain.entity.mybatis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kjh.domain.entity.BaseEntity;
import lombok.Data;

@Table(name = "faq")
@Entity
@Data
public class FaqEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fno;
	@Column(nullable = false)
	private String subject;
	@Column(nullable = false)
	private String content;
	
}
