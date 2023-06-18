package com.svs.member.api.dto;

import com.svs.member.domain.type.RoleName;

public record MemberDto() {

	public record MemberSignUpRequestDto(
			String userId, 
			String userPw, 
			String userName, 
			String email, 
			String phone, 
			RoleName roleName
			) {}
	
	public record MemberMatchPwDto(
			String pw
			) {}
	
	public record MemberUpdateDto(
			String userId, 
			String userPw, 
			String userName, 
			String email, 
			String phone
			) {}
	

}
