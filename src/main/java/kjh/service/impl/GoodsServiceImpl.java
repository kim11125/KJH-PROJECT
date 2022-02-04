package kjh.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import kjh.domain.dto.goods.GoodsDetailDto;
import kjh.domain.dto.goods.GoodsListDto;
import kjh.domain.dto.goods.GoodsSaveDto;
import kjh.domain.entity.GoodsEntity;
import kjh.domain.entity.GoodsEntityRepository;
import kjh.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	GoodsEntityRepository repository;
	
	//admin에 상품관리페이지
	@Override
	public String goodslist(Model model) {
		
		List<GoodsListDto> list = repository.findAll().stream()
							.map(GoodsListDto::new)
							.collect(Collectors.toList());
		
		model.addAttribute("list", list);
		
		return "/admin/goods/list";
	}
	
	//상품등록처리
	@Override
	public String goodsSave(MultipartFile goodsFile, GoodsSaveDto dto) throws IOException {
		
		//리눅스로 하는법
		String fileName = goodsFile.getOriginalFilename(); //저장될 파일명
		String location = "/home/ec2-user/src/root";
		String filePath = "/file/goods/"; //  root 경로 : /home/ec2-user/src/root
		goodsFile.transferTo(new File(location+filePath, fileName)); //파일 보내기
		
		GoodsEntity entity = GoodsEntity.builder()
						.fileName(fileName).filePath(filePath)
						.category(dto.getCategory()).title(dto.getTitle()).subTitle(dto.getSubTitle())
						.goodsDetail(dto.getGoodsDetail()).price(dto.getPrice()).stock(dto.getStock())
						.discount(dto.getDiscount())
						.build();
		repository.save(entity);
		
		return "redirect:/admin/goodslist";
	}
	
	//상품리스트 삭제
	@Override
	public String goodsDelete(long gno) {
		repository.deleteById(gno);
		return "redirect:/admin/goodslist";
	}
	
////////////////////////////////////////////////////////////////////
	//판매페이지로 뿌리기
	
	//판매페이지로 뿌리기
	@Override
	public String goodspage(Model model) {
		List<GoodsListDto> result = repository.findAll().stream()
				.map(GoodsListDto::new).collect(Collectors.toList());
		model.addAttribute("list", result);
		return "/admin/goods/goods-list";
	}

	@Override
	public String goodsDetail(long gno, Model model) {
		GoodsDetailDto result = repository.findById(gno)
										.map(GoodsDetailDto::new)
										.orElseThrow();
		
		model.addAttribute("detail", result);
		return "/goods/goodsDetail";
	}
	
	

}
