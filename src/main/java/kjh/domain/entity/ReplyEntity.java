package kjh.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import kjh.domain.dto.reply.ReplyEditDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ReplyEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rno;
	@Column(nullable = false)
	private String reply;
	@Column(nullable = false)
	private String replyer;
	
	@JoinColumn(name = "qno")
	@ManyToOne // 1개의 qna에는 reply 여러개가 담길수 있으므로 @ManyToOne설정
	private QnaEntity qna; //참조할 엔티티
	
	public ReplyEntity replyEdit(ReplyEditDto dto) {
			reply = dto.getReply();
			replyer = dto.getReplyer();
			return this;
	}
}	
