package kjh.controller.mybatis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kjh.domain.dto.mybatis.FaqDto;
import kjh.service.FaqService;
import kjh.service.impl.FaqServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FaqController {
	
	private final FaqService service;
	
	//faq리스트페이지
	@GetMapping("/faq")
	public String faqlist() {
		return "/custom/faq/faqList";
	}
	
	//faq쓰기페이지
	@GetMapping("/faq/write")
	public String faqwrite() {
		return "/custom/faq/faqWrite";
	}
	
	//faq쓰기 처리
	@PostMapping("/faq/write")
	public String faqwrite(FaqDto dto) {
		return service.faqwrite(dto);
	}
	
}
