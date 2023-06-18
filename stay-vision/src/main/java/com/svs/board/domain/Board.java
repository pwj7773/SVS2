package com.svs.board.domain;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.svs.member.domain.Member;
import com.svs.reply.domain.Reply;
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
@Table(name = "BOARD", schema = "svs")
public class Board extends BaseEntity {
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "CONTENT")
	private String content;
	
	@Builder.Default
	@Column(name = "INPUT_DATE")
	private String inputDate = ServerTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
	
	@Column(name = "VIEW_COUNT")
	private int viewCount;
	
	@Column(name = "BOARDTYPE")
	private String boardType;
	
	@Column(name = "ORIGINALFILE")
	private String originalFile;
	
	@Column(name = "SAVEDFILE")
	private String savedFile;
	
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
    @OneToMany(
            targetEntity = Reply.class
            , fetch = FetchType.LAZY
            , mappedBy = "boardNum")
	public List<Reply> reply;
}
