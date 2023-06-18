package com.svs.board.dto;


public record BoardRegisterDto() {

	public record BoardWriteDto(

			String title,
			String content,
			String boardType
			
			) {
	}
	
	public record BoardUpdateDto(

			Long id,
			String title,
			String content,
			String boardType
			
			) {
	}

}
