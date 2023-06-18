package com.svs.business.api;


import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.svs.business.api.dto.BusinessRegisterDto.BusinessInsertRequestDto;
import com.svs.business.domain.Business;
import com.svs.business.service.BusinessService;
import com.svs.member.domain.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BusinessApi {

	private final BusinessService businessService;
	
	
	// ROLE_ADMIN, ROLE_EMP의 전체 숙박업소 정보 조회
	@GetMapping("/infoBusiness")
	public String infoBusiness(String category, String keyword, Model model) {
		
		List<Business> bList = businessService.findAllBusiness(category, keyword);
		model.addAttribute("blist",bList);
		model.addAttribute("category", category);
		model.addAttribute("keyword", keyword);
		
		return "business/infoBusiness";
	}
	
	
	// 숙박업소 정보 추가 페이지 이동
	@GetMapping("/insertBusiness")
	public String insertBusiness() {
		return "business/insertBusiness";
	}
	
	// 숙박업소 정보 추가
	@PostMapping("/insertBusiness")
	public String insertBusiness(BusinessInsertRequestDto dto, @AuthenticationPrincipal Member member) {
		// 시큐리티에서 사용자 id를 가져옴
		businessService.insert(dto,member.getUserId());
		
		return "redirect:/infoBusiness";
	}
	
	// 숙박업소 정보 수정 페이지 이동
		@GetMapping("/updateBusiness/{id}")
		public String updateBusiness(@PathVariable Long id, Model model) {
			Business business = businessService.findById(id);
		    model.addAttribute("business", business);
			
			return "business/updateBusiness";
		}
		
		// 숙박업소 정보 수정
		@PostMapping("/updateBusiness")
		public String updateBusiness(BusinessInsertRequestDto dto) {
			businessService.updateBusiness(dto);
			
			return "redirect:/infoBusiness";
		}
	
	// 지도 띄위기
	@GetMapping("/popup/{id}")
	public String popup(@PathVariable Long id, Model model) {
		
		Business business = businessService.findById(id);
	    model.addAttribute("business", business);
	    return "business/popup";
	}
}
