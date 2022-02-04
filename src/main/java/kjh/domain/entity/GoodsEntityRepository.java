package kjh.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsEntityRepository extends JpaRepository<GoodsEntity, Long>{

}
