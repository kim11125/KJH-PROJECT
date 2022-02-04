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

import kjh.domain.dto.goods.GoodsSaveDto;
import kjh.service.GoodsService;
import kjh.service.impl.GoodsServiceImpl;

@Controller
public class GoodsController {
	
	@Autowired
	GoodsService service;
	
///////////////////////////////////////////////////
			/* admin */
///////////////////////////////////////////////////
	//상품리스트 페이지 이동
	@GetMapping("/admin/goodslist")
	public String goodslist(Model model) {
		return service.goodslist(model);
	}
	
	//상품등록페이지 이동
	@GetMapping("/admin/goodswrite")
	public String goodsSave() {
		return "/admin/goods/write";
	}
	
	//상품등록처리
	@PostMapping("/goods/write")
	public String goodsSave(MultipartFile goodsFile, GoodsSaveDto dto) throws IOException {
		return service.goodsSave(goodsFile, dto);
	}
	
	//상품삭제
	@DeleteMapping("/goods/{gno}")
	public String goodsDelete(@PathVariable long gno) {
		return service.goodsDelete(gno);
	}
	
///////////////////////////////////////////////////
			/* 판매페이지 */
///////////////////////////////////////////////////
	//판매페이지로 이동
	@GetMapping("/goods")
	public String goodsList() {
		return "/goods/goodsList";
	}
	
	//판매페이지에 뿌리기
	@GetMapping("/goodspage")
	public String goodspage(Model model) {
		return service.goodspage(model);
	}
	
	//상세페이지로 이동
	@GetMapping("/goods/{gno}")
	public String goodsDetail(@PathVariable long gno, Model model) {
		return service.goodsDetail(gno, model);
	}
	
}
