package com.svs.board.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.svs.board.domain.Board;
import com.svs.board.dto.BoardRegisterDto.*;
import com.svs.board.service.BoardService;
import com.svs.member.domain.Member;
import com.svs.support.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardApi {

	private final BoardService boardService;

	private final String REDIRECT_LIST = "redirect:/boardList";
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;

	// 글 목록보기
	@GetMapping("boardList")
	public String boardList(String id, String category, String keyword,
			@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Page<Board> board = boardService.findAll(category, keyword, id, page);
		model.addAttribute("board", board);
		model.addAttribute("keyword", keyword);
		model.addAttribute("category", category);
		model.addAttribute("id", id);
		return "board/boardList";
	}

	// 글쓰기화면 보여주기
	@GetMapping("boardWrite")
	public String boardWrite(String id, Model model) {
		model.addAttribute("id", id);
		return "board/boardWrite";
	}

	// 글쓰기
	@PostMapping("boardWrite")
	public String boardWrite(BoardWriteDto dto, @AuthenticationPrincipal Member member,
			@RequestParam MultipartFile file) {
		Board board = Board.builder().userId(member.getUserId()).title(dto.title()).content(dto.content())
				.boardType(dto.boardType()).build();
		// file이 있으면 저장하고 아니면 건너뛰자...
		if (file.isEmpty() == false) { // 파일이 비어있지 않다면
			// FileService 사용해서 파일 저장
			String savedFileName = FileService.saveFile(file, uploadPath);
			board = Board.builder().userId(member.getUserId()).title(dto.title()).content(dto.content())
					.boardType(dto.boardType()).originalFile(file.getOriginalFilename()).savedFile(savedFileName)
					.build();
		}
		boardService.boardWirte(board);
		return REDIRECT_LIST + "?id=" + dto.boardType();
	}

	// 글보기
	@GetMapping("boardRead")
	public String boardRead(Long boardNum, Model model) {
		boardService.addBoardViewCount(boardNum);
		Board board = boardService.findById(boardNum).get();
		model.addAttribute(board);
		return "board/boardRead";
	}

	// 글 삭제
	@GetMapping("boardDelete")
	public String boardDelete(Long boardNum) {
		String id = boardService.findById(boardNum).get().getBoardType();
		boardService.boardDelete((long) boardNum);
		return REDIRECT_LIST + "?id=" + id;
	}

	// 수정화면 보여주기
	@GetMapping("boardUpdate")
	public String boardUpdate(Long boardNum, Model model) {
		Board board = boardService.findById(boardNum).get();
		model.addAttribute(board);
		return "board/boardUpdate";
	}

	// 글 수정
	@PostMapping("boardUpdate")
	public String boardUpdate(BoardUpdateDto dto, @RequestParam MultipartFile file) {
		boardService.boardUpdate(dto, file);
		return REDIRECT_LIST + "?id=" + dto.boardType();
	}

	// 파일 다운로드 받기
	@GetMapping("download")
	public String downloadFile(Long boardNum, HttpServletResponse response) {

		// 글 정보 조회
		Board board = boardService.findById(boardNum).get();

		// 원래 파일
		String originalFile = board.getOriginalFile();

		// 응답 객체 준비
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(originalFile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 저장된 파일 가져오기
		String fullPath = uploadPath + "/" + board.getSavedFile();

		// 파일을 읽을 파일 스트림하고 클라이언트에 전달할 출력스트림
		FileInputStream fis = null;
		ServletOutputStream sos = null;

		try {
			// 서버에 저장된 파일 주소에 있는 파일을 입력스트림에 읽어 옴
			fis = new FileInputStream(fullPath);
			// 응답 객체의 출력스트림 설정
			sos = response.getOutputStream();

			// Spring이 제공하는 파일관련 유틸 사용해서 입력 -> 출력 스트림으로 파일 이동
			FileCopyUtils.copy(fis, sos);

			// 사용이 끝난 스트림은 닫는다
			fis.close();
			sos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return REDIRECT_LIST + "?id=" + board.getBoardType(); // 얘는 동작안함
	}
	
	@GetMapping("display")
	public ResponseEntity<Resource> display(Long boardNum) {

		Board board = boardService.findById(boardNum).get();

		log.debug("Board : {}", board);
		// 저장된 파일 가져오기
		String fullPath = uploadPath + "/" + board.getSavedFile();

		Resource resource = new FileSystemResource(fullPath);

		// 리소스가 없으면 NOT_FIND error 발생
		if (!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}

		HttpHeaders header = new HttpHeaders();

		Path filePath = null;

		try {
			filePath = Paths.get(fullPath);
			header.add("Content-type", Files.probeContentType(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	@PostMapping("/summerimages")
	@ResponseBody
	public String summerimages(@RequestParam MultipartFile file) {
		log.debug("File : {}", file.getOriginalFilename());
		String savedFileName = FileService.saveFile(file, uploadPath);
		log.debug("savedFileName : {}", savedFileName);
		

		return "/image/"+savedFileName;
	}
	
	@GetMapping("image/{filename:.+}")
	public ResponseEntity<Resource> getImage(@PathVariable String filename) throws MalformedURLException, IOException {
	    Path path = Paths.get("C:/upload/" + filename);
	    log.debug("{}",path);
	    UrlResource resource = new UrlResource(path.toUri());
	    log.debug("{}",resource);
	    return ResponseEntity.ok()
	            .body(resource);
	}
	
}
