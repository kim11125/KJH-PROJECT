package kjh.mapper;

import org.apache.ibatis.annotations.Mapper;

import kjh.domain.dto.mybatis.FaqDto;

@Mapper
public interface FaqMapper {

	void save(FaqDto dto);

}
