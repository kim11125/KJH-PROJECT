package kjh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	//관리자페이지로 이동
	@GetMapping("/admin")
	public String adminPage() {
		return "/admin/default";
	}
	
}
