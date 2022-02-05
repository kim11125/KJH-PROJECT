package kjh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kjh.domain.dto.member.MemberSaveDto;
import kjh.domain.dto.member.MemberUpdateDto;
import kjh.security.MyUserDetails;
import kjh.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	//로그인페이지 이동
	@GetMapping("/loginpage")
	public String loginPage() {
		return "/member/login";
	}
	
	//회원가입 이전에 약관동의 페이지로 이동
	@GetMapping("/join")
	public String joinPage() {
		return "/member/join";
	}
	
	//약관페이지에서 회원가입 버튼 눌렀을때
	@GetMapping("/register")
	public String registerForm() {
		return "/member/register";
	}
	
	//회원가입처리
	@PostMapping("/register")
	public String register(MemberSaveDto dto) {
		return service.register(dto);
	}
	
	//로그아웃페이지
	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/";
	}
	
///////////////////////////////////////////////////////////
	//회원정보 페이지 이동
	@GetMapping("/user")
	public String userPage() {
		return "/member/member";
	}
	
	//회원정보 수정페이지 이동
	@GetMapping("/userInfo")
	public String userInfo(@AuthenticationPrincipal MyUserDetails user, Model model) {
		return service.userInfo(user, model);
	}
	
	//회원정보 수정처리
	@ResponseBody
	@PutMapping("/user")
	public void UserInfoUpdate(MemberUpdateDto dto) {
		service.UserInfoUpdate(dto);
	}
	
	//회원탈퇴 페이지 이동
	@GetMapping("/withdraw")
	public String withdraw() {
		return "/member/withdraw";
	}
	
	//회원탈퇴처리
	@ResponseBody
	@DeleteMapping
	public void withdraw(@PathVariable long mno) {
		service.withdraw(mno);
	}
	
	
}
