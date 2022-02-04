package kjh.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kjh.domain.dto.qna.QnaUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class QnaEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long qno;
	@Column(nullable = false)
	private String category;
	@Column(nullable = false)
	private String subject;
	@Column(nullable = false)
	private String content;
	
	@JoinColumn(name = "mno")
	@ManyToOne
	private MemberEntity member;

	public QnaEntity qnaUpdate(QnaUpdateDto dto) {
		category = dto.getCategory();
		subject = dto.getSubject();
		content = dto.getContent();
		return this;
	}

	
}
