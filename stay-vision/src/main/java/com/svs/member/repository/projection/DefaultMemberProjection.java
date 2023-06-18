package com.svs.member.repository.projection;



import com.svs.member.domain.type.RoleName;

public record DefaultMemberProjection() {
	
	public record MemberProjection(
			String userId, 
			String userName, 
			RoleName roleName
			) {}
	
	public record MemberInfoProjection(
			String userId, 
			String userName, 
			String email, 
			String phone
			) {}
}
