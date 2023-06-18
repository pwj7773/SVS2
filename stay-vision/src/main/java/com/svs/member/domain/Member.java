package com.svs.member.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.svs.board.domain.Board;
import com.svs.business.domain.Business;
import com.svs.member.domain.type.AccountStatus;
import com.svs.member.domain.type.RoleName;
import com.svs.reply.domain.Reply;
import com.svs.support.BaseEntity;

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
@Table(name = "MEMBER", schema = "svs")
public class Member extends BaseEntity {

	@Column(name="USER_ID")
	String userId;
	
	@Column(name="USER_PW")
	String userPw;
	
	@Column(name="USER_NAME")
	String userName;
	
	@Column
	String email;
	
	@Column
	String phone;
	
	@Column(name="ROLENAME")
	@Builder.Default
	@Enumerated(EnumType.STRING)
	RoleName roleName = RoleName.ROLE_USER;
	
	@Column
	@Builder.Default
	@Enumerated(EnumType.STRING)
	AccountStatus status = AccountStatus.SLEEP;
	
    @OneToMany(
            targetEntity = Business.class
            , fetch = FetchType.EAGER
            , mappedBy = "userId")
	public List<Business> businesses;
    
    @OneToMany(
            targetEntity = Business.class
            , fetch = FetchType.EAGER
            , mappedBy = "userId")
	public List<Board> board;
    
    @OneToMany(
            targetEntity = Reply.class
            , fetch = FetchType.EAGER
            , mappedBy = "userId")
	public Set<Reply> reply;
}
