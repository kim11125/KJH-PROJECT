package kjh.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {


	Optional<MemberEntity> findByUserId(String username);

	//Optional<MyUserDetails> findById(String email);

	Optional<MemberEntity> findByEmail(String email);

	Optional<MemberEntity> findByUserId(long mno);


}
