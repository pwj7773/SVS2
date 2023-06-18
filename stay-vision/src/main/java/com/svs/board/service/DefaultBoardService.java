package com.svs.board.service;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.svs.board.domain.Board;
import com.svs.board.dto.BoardRegisterDto.BoardUpdateDto;
import com.svs.board.repository.BoardRepository;
import com.svs.support.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public final class DefaultBoardService implements BoardService {
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	private final BoardRepository boardRepository;
	
	
	@Override
	public Page<Board> findAll(String category, String keyword, String boardType, int page) {
		
		PageRequest pageRequest = PageRequest.of(page, 10, Sort.Direction.DESC,"id");
		Page<Board> board = null;
		
		if (keyword == null || keyword.equals("")){
			board = boardRepository.findByBoardType(boardType, pageRequest);
		}
		else if (category.equals("title")) {
			board = boardRepository.findByBoardTypeAndTitleContains(boardType,keyword, pageRequest);
		}
		else if (category.equals("content")) {
			board = boardRepository.findByBoardTypeAndContentContains(boardType,keyword, pageRequest);
		}
		else if (category.equals("userId")) {
			board = boardRepository.findByBoardTypeAndUserIdContains(boardType,keyword, pageRequest);
		}
		
		return board;
		
		
	}

	@Override
	public void boardWirte(Board board) {

		boardRepository.save(board);
	}

	@Override
	public Optional<Board> findById(Long boardNum) {
		return boardRepository.findById(boardNum);
	}

	@Override
	public void addBoardViewCount(Long boardNum) {
		Board board = boardRepository.findById(boardNum).get();
		board.setViewCount(board.getViewCount() + 1);
		boardRepository.save(board);
	}

	@Override
	public void boardDelete(Long boardNum) {
		boardRepository.deleteById(boardNum);
	}

	@Override
	public void boardUpdate(BoardUpdateDto dto, MultipartFile file) {
		Board board = boardRepository.findById(dto.id()).get();
		// file이 있으면 저장하고 아니면 건너뛰자...
		if (file.isEmpty() == false) { // 파일이 비어있지 않다면
			// FileService 사용해서 파일 저장
			String savedFileName = FileService.saveFile(file, uploadPath);
			board.setOriginalFile(file.getOriginalFilename());
			board.setSavedFile(savedFileName);
		}
		board.setContent(dto.content());
		board.setTitle(dto.title());
		boardRepository.save(board);
	}





}
