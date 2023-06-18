package com.svs.reply.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.svs.reply.domain.Reply;
import com.svs.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public final class DefaultReplyService implements ReplyService {
	
	private final ReplyRepository replyRepository;
	
	@Override
	public void insertReply(Reply reply) {
	
		replyRepository.save(reply);
	}

	@Override
	public List<Reply> getAllReply(Long boardNum) {
		return replyRepository.findAllByBoardNum(boardNum);
	}

	@Override
	public Page<Reply> getAllReply(Long boardNum, Pageable pageable) {
		return replyRepository.findAllByBoardNum(boardNum, pageable);
	}

	@Override
	public Optional<Reply> getOneReply(Long replyNum) {
		return replyRepository.findById(replyNum);
	}

	@Override
	public void deleteReply(Long replyNum) {
		replyRepository.deleteById(replyNum);
	}

	@Override
	public void updateReply(String replyText, Long replyNum) {
		Reply reply = replyRepository.findById(replyNum).get();
		reply.setReplyText(replyText);
		replyRepository.save(reply);
	}


}
