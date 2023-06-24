package com.svs.predict.api.dto;


public record PredictDto() {
	
	
	public record ResrvationDto(
		    String platform,
		    String name,
		    String address,
		    String room,
		    String date
			) {}
	

}
