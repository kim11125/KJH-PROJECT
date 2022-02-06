package kjh.service.impl;

import org.springframework.stereotype.Service;

import kjh.domain.dto.mybatis.FaqDto;
import kjh.mapper.FaqMapper;
import kjh.service.FaqService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FaqServiceImpl implements FaqService {
	
	private final FaqMapper faqMapper;
	
	@Override
	public String faqwrite(FaqDto dto) {
		faqMapper.save(dto);
		System.out.println("작성완료");
		return null;
	}

}
