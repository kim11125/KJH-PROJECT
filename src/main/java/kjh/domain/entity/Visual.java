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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Visual extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long vno;
	@Column(nullable = false)
	private String filePath;
	@Column(nullable = false)
	private String fileName;
	@Column(nullable = false)
	private long fileSize;
	
	private String title;
	private String subTitle;
	
	private int orderNo;
	
}
