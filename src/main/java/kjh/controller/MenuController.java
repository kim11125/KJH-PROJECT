package kjh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
	
	//찾아오시는길 페이지 이동
	@GetMapping("/info/locate")
	public String locatePage() {
		return "/info/locate";
	}
	
	//as규정 페이지 이동
	@GetMapping("/custom/aspolicy")
	public String aspolicyPage() {
		return "/custom/aspolicy";
	}
	
}
