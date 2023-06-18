package com.svs.board.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.svs.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	Page<Board> findByBoardType(String boardType, Pageable pageable);

	Page<Board> findByBoardTypeAndTitleContains(String boardType, String keyword, Pageable pageable);

	Page<Board> findByBoardTypeAndContentContains(String boardType, String keyword, Pageable pageable);

	Page<Board> findByBoardTypeAndUserIdContains(String boardType, String keyword, Pageable pageable);



	
}
