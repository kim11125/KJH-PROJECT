package kjh.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import kjh.domain.dto.visual.VisualListDto;
import kjh.domain.dto.visual.VisualSaveDto;
import kjh.domain.entity.Visual;
import kjh.domain.entity.VisualRepository;
import kjh.service.VisualService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class VisualServiceImpl implements VisualService {
	
	@Autowired
	private VisualRepository repository;
	
	//이미지 등록 처리
	@Override
	public void saveAndFile(MultipartFile visualFile, VisualSaveDto dto) throws IOException {

		long fileSize = visualFile.getSize(); //파일 사이즈정보
		String fileName = visualFile.getOriginalFilename(); //저장될 파일이름
		String location = "/home/ec2-user/src/root";
		String filePath = "/file/visual/"; //  root 경로 : /home/ec2-user/src/root
		
		visualFile.transferTo(new File(location+filePath, fileName)); //파일 보내기
		
		
		int size = repository.findAll().size();

		Visual entity = Visual.builder()
				.fileSize(fileSize).filePath(filePath).fileName(fileName)
				.title(dto.getTitle()).subTitle(dto.getSubTitle()).orderNo(size+1)
				.build();

		repository.save(entity);
		
	}
	
	//이미지 리스트 페이지
	@Override
	public String getlist(Model model) {
//		List<VisualListDto> result = repository.findAll().stream()
//				.map(VisualListDto :: new)
//				.collect(Collectors.toList());
//		변수 안만들고 직접 입력
		model.addAttribute("list", repository.findAll().stream()
									.map(VisualListDto :: new)
									.collect(Collectors.toList()));
		
//		List<Visual> result2 = repository.findAll();
//		List<VisualListDto> list2 = new Vector<>();
//		for(Visual entity : result2) {
//			VisualListDto dto = new VisualListDto(entity);
//			list2.add(dto);
//		}
		
		return "/admin/visual/list";
	}

	@Override
	public String getPageList(Model model) {
		model.addAttribute("list", repository.findAll().stream()
								.map(VisualListDto :: new)
								.collect(Collectors.toList()));
		return "/admin/visual/page-list";
	}
	
	//비주얼 삭제처리
	@Override
	public String delete(long vno) {
		repository.deleteById(vno);
		return "redirect:/admin/visuallist";
	}

}
