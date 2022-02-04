package kjh.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import kjh.domain.dto.visual.VisualSaveDto;
import kjh.service.VisualService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class VisualController {
	
	@Autowired
	private VisualService service;
	
	//비주얼이미지 리스트 페이지로 이동
	@GetMapping("/visuals")
	public String visualList(Model model) {
		return service.getPageList(model);
	}
	
	//이미지 등록 페이지 이동
	@GetMapping("/admin/visualpage")
	public String visualpage() {
		return "admin/visual/write";
	}
	
	//비주얼 리스트 페이지
	@GetMapping("/admin/visuallist")
	public String list(Model model) {
		return service.getlist(model);
	}
	
	//비주얼 등록처리
	@PostMapping("/admin/visuals")
	public String save(MultipartFile visualFile, VisualSaveDto dto) throws IOException{
		service.saveAndFile(visualFile, dto);
		return "redirect:/admin/visuallist";
	}
	
	//비주얼 삭제처리
	@DeleteMapping("/custom/visual/{vno}")
	public String delete(@PathVariable long vno) {
		return service.delete(vno);
	}
}
