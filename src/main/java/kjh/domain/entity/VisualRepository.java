package kjh.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisualRepository extends JpaRepository<Visual, Long>{

}
