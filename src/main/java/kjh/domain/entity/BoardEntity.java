package kjh.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import kjh.domain.dto.board.BoardUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BoardEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bno;
	@Column(nullable = false)
	private String category;
	@Column(nullable = false)
	private String subject;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private String writer;
	@Column(nullable = false)
	private int readCount;
	
	
	//조회수 1업처리
	public BoardEntity readCountUp() {
		readCount++;
		return this;
	}
	
	//update처리를 위한 메서드
	public BoardEntity update(BoardUpdateDto dto) {
		category = dto.getCategory();
		subject = dto.getSubject();
		content = dto.getContent();
		writer = dto.getWriter();
		updatedDate = dto.getUpdatedDate();
		return this;
		
	}
	
	
	
}
