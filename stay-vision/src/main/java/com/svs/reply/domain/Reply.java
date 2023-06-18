package com.svs.reply.domain;

import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.svs.board.domain.Board;
import com.svs.member.domain.Member;
import com.svs.support.BaseEntity;
import com.svs.support.time.ServerTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "REPLY", schema = "svs")
public class Reply extends BaseEntity {

	@Column(name = "REPLYTEXT")
    private String replyText;
    
    @Column(name = "BOARDNUM")
    private Long boardNum; 
    
	@Column(name = "USER_ID")
	private String userId;
    
    @Builder.Default
	@Column(name = "INPUT_DATE")
	private String inputDate = ServerTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    
	@ManyToOne(
			targetEntity = Member.class,
			fetch = FetchType.LAZY, 
			optional = false)
	@JoinColumn(
			name = "USER_ID", 
			referencedColumnName = "USER_ID", 
			insertable = false, 
			updatable = false)
	Member member;
	
	@JsonIgnore
	@ManyToOne(
			targetEntity = Board.class,
			fetch = FetchType.LAZY, 
			optional = false)
	@JoinColumn(
			name = "BOARDNUM", 
			referencedColumnName = "id", 
			insertable = false, 
			updatable = false)
	Board board;
}
