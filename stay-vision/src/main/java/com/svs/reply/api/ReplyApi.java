package com.svs.reply.api;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.svs.member.domain.Member;
import com.svs.reply.domain.Reply;
import com.svs.reply.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReplyApi {

	private final ReplyService replyService;
	
	// 댓글 쓰기
	@PostMapping("/insertReply")
	public String insertReply(String replyText, Long boardNum, @AuthenticationPrincipal Member member) {
		Reply reply = Reply.builder()
				.replyText(replyText)
				.boardNum(boardNum)
				.userId(member
				.getUserId())
				.build();
		replyService.insertReply(reply);
		return "OK";

	}
	
	// 댓글 불러오기
	@GetMapping("/boards/{boardNum}/replies") // GET/POST/PUT/DELETE + "/boards/{boardId}/reply/{replyId}"
	public Page<Reply> findReplies(
			@PathVariable Long boardNum,
			@PageableDefault(size=5, sort="inputDate", direction = Sort.Direction.DESC)
			Pageable pageable) { // ?page=1&size=10 ...
		pageable = pageable.previousOrFirst(); // 1 -> 0, 0 -> 0, -200 -> 0
		Page<Reply> replies = replyService.getAllReply(boardNum, pageable);
		
		return replies;
	}
	
	// 댓글 선택
	@PostMapping("/getOneReply")
	public Reply getOneReply(Long replyNum) {
		Reply reply = replyService.getOneReply(replyNum).get();
		return reply;
	}
	
	// 댓글 삭제
	@PostMapping("/deleteReply")
	public void deleteReply(Long replyNum) {
		replyService.deleteReply(replyNum);
	}
	
	// 댓글 수정
	@PostMapping("/updateReply")
	public String updateReply(String replyText, Long replyNum) {
	
		replyService.updateReply(replyText,replyNum);
		return "OK";
	}
}
