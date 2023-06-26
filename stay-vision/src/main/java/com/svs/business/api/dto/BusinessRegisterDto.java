package com.svs.business.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record BusinessRegisterDto() {

	public record BusinessInsertRequestDto(

			Long id,
			@NotNull
			String businessName,
			@NotNull
			String businessAddress,
			@Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$")
			String phone,
		    String userId
			) {
	}

}
