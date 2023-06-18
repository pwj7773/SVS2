package com.svs.business.api.dto;


public record BusinessRegisterDto() {

	public record BusinessInsertRequestDto(

			Long id,
			String businessName,
			String businessAddress,
			int totalRoom,
			String phone,
		    String userId
			) {
	}

}
