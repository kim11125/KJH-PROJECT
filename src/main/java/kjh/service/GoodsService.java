package kjh.service;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import kjh.domain.dto.goods.GoodsSaveDto;

public interface GoodsService {

	//admin
	String goodsSave(MultipartFile goodsFile, GoodsSaveDto dto) throws IOException;
	
	String goodslist(Model model);
	
	String goodsDelete(long gno);

//////////////////////////////////////////
	//판매페이지로 뿌리기

	String goodspage(Model model);

	String goodsDetail(long gno, Model model);
	
}
