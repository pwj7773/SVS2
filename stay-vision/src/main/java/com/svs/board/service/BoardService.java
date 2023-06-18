package com.svs.board.service;


import com.svs.board.domain.Board;
import com.svs.board.dto.BoardRegisterDto.BoardUpdateDto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;


public interface BoardService {

	Page<Board> findAll(String category, String keyword, String boardType, int page);

	void boardWirte(Board board);

	Optional<Board> findById(Long boardNum);

	void addBoardViewCount(Long boardNum);

	void boardDelete(Long boardNum);

	void boardUpdate(BoardUpdateDto dto, MultipartFile file);



}
