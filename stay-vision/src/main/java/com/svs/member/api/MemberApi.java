package com.svs.member.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svs.member.domain.Member;
import com.svs.member.domain.type.AccountStatus;
import com.svs.member.repository.projection.DefaultMemberProjection.*;
import com.svs.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.svs.member.api.dto.MemberDto.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberApi {
	private final MemberService memberservice;
	private final PasswordEncoder passwordEncoder;

	// 회원 가입하기(화면 띄우기)
	@GetMapping("/insert")
	public String insert() {
		return "/member/insert";
	}
	
	//회원가입 유효성 검사
	@PostMapping("/checkId")
	@ResponseBody
	public String checkId(String userId) {
		Optional<Member> member = memberservice.findByUserId(userId);
		if(member.isEmpty()) {
			return "OK";
		}else {
			return "NG";
		}
	}
	
	// 회원 가입 하기
	@PostMapping("insert")
	public String signUp(@Valid MemberSignUpRequestDto body) {
		memberservice.signUp(body);
		return "redirect:/";
	}

	// 로그인 화면
	@GetMapping("/login")
	public String login() {
		return "/member/login";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null)
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		return "redirect:/";
	}

	// 회원 관리 화면
	@GetMapping("/adminapproval")
	public String adminapproval(Model model) {

		// 전체 목록
		List<MemberProjection> mList = memberservice.findAllByStatus(AccountStatus.ACTIVE);
		model.addAttribute("mlist", mList);

		// 가입 대기 목록
		List<MemberProjection> sList = memberservice.findAllByStatus(AccountStatus.SLEEP);
		model.addAttribute("slist", sList);
		return "/member/adminapproval";
	}

	// 회원 승인
	@Transactional
	@PostMapping(value = "/adminapproval", params = "approval")
	public String adminapproval(@RequestParam String[] checkedValue2) {
		for (String userId : checkedValue2) {
			if (userId.equals(""))
				continue;
			Member member = memberservice.findByUserId(userId).get();
			member.setStatus(AccountStatus.ACTIVE);
		}

		return "redirect:/adminapproval";
	}

	// 회원 거절
	@PostMapping(value = "/adminapproval", params = "refuse")
	public String adminrefuse(@RequestParam String[] checkedValue2) {
		for (String userId : checkedValue2) {
			if (userId.equals(""))
				continue;
			memberservice.deleteByUserId(userId);
		}
		return "redirect:/adminapproval";
	}

	// 회원 삭제
	@PostMapping(value = "/memberdelete", params = "delete")
	public String memberdelete(@RequestParam String[] checkedValue1) {
		for (String userId : checkedValue1) {
			if (userId.equals(""))
				continue;
			memberservice.deleteByUserId(userId);
		}

		return "redirect:/adminapproval";
	}

	// 회원 비활성화
	@Transactional
	@PostMapping(value = "/memberdelete", params = "disabled")
	public String memberdisabled(@RequestParam String[] checkedValue1) {
		for (String userId : checkedValue1) {
			if (userId.equals(""))
				continue;
			Member member = memberservice.findByUserId(userId).get();
			member.setStatus(AccountStatus.SLEEP);
		}

		return "redirect:/adminapproval";
	}

	// 내 정보 보기
	@GetMapping("/myinfo")
	public String myinfo(@AuthenticationPrincipal Member user, Model model) {
		String id = user.getUserId();
		MemberInfoProjection member = memberservice.MemberInfo(id).get();
		model.addAttribute("member", member);
		return "/member/myinfo";
	}

	// 회원 비번 확인 페이지
	@GetMapping("/reauthenticate")
	public String reauthenticate() {
		return "/member/reauthenticate"; // 새로운 인증 정보를 입력받을 폼을 보여줌
	}

	// 회원 비번 확인
	@PostMapping("/reauthenticate")
	public String reauthenticate(@AuthenticationPrincipal Member member, @Valid MemberMatchPwDto dto, Model model) {

		int result = memberservice.checkMember(member.getUserId(), dto.pw());

		if (result != 0) { // affected row(영향을 받은 행이 1개 이상)
			return "redirect:/memberupdate";
		} else {
			model.addAttribute("msg", "비밀번호가 틀렸습니다.");
			return "/member/reauthenticate";
		}

	}

	// 회원 정보 수정페이지
	@GetMapping("/memberupdate")
	public String memberupdate(@AuthenticationPrincipal Member member, Model model) {

		String id = member.getUserId();
		member = memberservice.findByUserId(id).get();
		model.addAttribute("member", member);

		return "/member/memberupdate";
	}

	// 회원 정보 수정
	@Transactional
	@PostMapping("/memberupdate")
	public String memberupdate(@Valid MemberUpdateDto dto) {
		Member member = memberservice.findByUserId(dto.userId()).get();
		member.setUserPw(passwordEncoder.encode(dto.userPw()));
		member.setUserName(dto.userName());
		member.setEmail(dto.email());
		member.setPhone(dto.phone());
		return "redirect:/myinfo";
	}

	// 예약 통계 (임시)
	@GetMapping("/reservation")
	public String reservation() {

		return "reservation";
	}
}
