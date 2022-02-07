package kjh.service;

import org.springframework.ui.Model;

import kjh.domain.dto.mybatis.FaqDto;

public interface FaqService {

	String faqwrite(FaqDto dto);

	String faqlist(FaqDto dto, Model model);

	String faqdelete(long fno);

}
