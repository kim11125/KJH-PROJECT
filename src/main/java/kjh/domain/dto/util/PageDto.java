package kjh.domain.dto.util;

import lombok.Getter;

@Getter
public class PageDto {

	int from;	//각 페이지 그룹의 시작번호
	int to;		//각 페이지 그룹의 끝번호
	int pageTot;//총페이지수
	
	
	/**
	 * @param pageGroup	: 한 화면에 표현할 페이지 수
	 * @param page		: 시작페이지번호
	 * @param pageTot	: 총 페이지 수 
	 */
	public PageDto(int pageGroup, int page, int pageTot) {
		this.pageTot = pageTot;
		
		//{페이지 번호}	:	페이지그룹
		//{1,2,3,4,5}	:  pageGroupNo = 1
		//{6,7,8,9,10}	:  pageGroupNo = 2
		
		//페이지 그룹
		//1/5(0) 2/5(0) 3/5(0) 4/5(0) 5/5(1)  
		int pageGroupNo = page / pageGroup;
		
		//한화면에 표현할 페이지 수 이외에 나머지가 더 있으면 페이지 한개 추가
		if(page % pageGroup > 0) pageGroupNo++;
		
		//각 페이지 그룹의 마지막 번호 계산
		//페이지그룹번호 * 한 화면에 표현할 페이지 갯수
		to = pageGroupNo * pageGroup;
		
		//각 페이지 그룹의 시작번호 계산
		//마지막번호 - 한 화면에 표현할 페이지 갯수 + 1
		from = to - pageGroup + 1;
		
		//to를 pageGroup만큼 곱했으니 마지막그룹의 끝번호가 더 클수있음
		//마지막 번호는 총 페이지
		if(to > pageTot) to = pageTot;
	}
	
	
	
}
