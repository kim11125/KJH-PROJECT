package kjh.service;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import kjh.domain.dto.visual.VisualSaveDto;

public interface VisualService {

	void saveAndFile(MultipartFile visualFile, VisualSaveDto dto) throws IOException;

	String getlist(Model model);

	String getPageList(Model model);

	String delete(long vno);

}
