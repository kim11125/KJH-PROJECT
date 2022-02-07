package kjh.controller.mybatis;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kjh.domain.dto.mybatis.FaqDto;
import kjh.service.FaqService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FaqController {
	
	private final FaqService service;
	
	//faq리스트페이지
	@GetMapping("/faq")
	public String faqlist(FaqDto dto, Model model) {
		return service.faqlist(dto, model);
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
	
	//faq삭제처리
	@ResponseBody
	@DeleteMapping("/faq/delete/{fno}")
	public String faqdelete(long fno) {
		return service.faqdelete(fno);
	}
}
