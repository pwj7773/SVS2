package com.svs.reply.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.svs.reply.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	List<Reply> findAllByBoardNum(Long boardNum);
	Page<Reply> findAllByBoardNum(Long boardNum, Pageable pageable);
}
