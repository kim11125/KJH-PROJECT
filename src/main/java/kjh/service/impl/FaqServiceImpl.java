package kjh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kjh.domain.dto.mybatis.FaqDto;
import kjh.domain.entity.mybatis.FaqEntity;
import kjh.domain.entity.mybatis.FaqEntityMapper;
import kjh.service.FaqService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FaqServiceImpl implements FaqService {
	
	private final FaqEntityMapper mapper;
	
	//faq리스트
	@Override
	public String faqlist(FaqDto dto, Model model) {
		List<FaqEntity> result = mapper.findAll();
		model.addAttribute("list", result);
		return "/custom/faq/faqList";
	}
	
	//글쓰기처리
	@Override
	public String faqwrite(FaqDto dto) {
		mapper.save(dto);
		//System.out.println("글작성됨");
		return "redirect:/faq";
	}

}
