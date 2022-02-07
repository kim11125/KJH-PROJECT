package kjh.domain.entity.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kjh.domain.dto.mybatis.FaqDto;

@Mapper
public interface FaqEntityMapper {
	
	//faq list
	List<FaqEntity> findAll();

	//faq 저장
	void save(FaqDto dto);
	

	
}
