package com.svs.business.repository.projection;

import com.svs.member.domain.Member;

public record DefaultBusinessProjection() {
	
	public record BusinessFindProjection(
			String businessName,
			Long id,
			String businessAddress,
			String phone,
			Member member
			){}


}
