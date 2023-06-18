package com.svs.reply.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svs.reply.domain.Reply;

public interface ReplyService {

	void insertReply(Reply reply);

	List<Reply> getAllReply(Long boardNum);
	Page<Reply> getAllReply(Long boardNum, Pageable pageable);

	Optional<Reply> getOneReply(Long replyNum);

	void deleteReply(Long replyNum);

	void updateReply(String replyText, Long replyNum);

}
