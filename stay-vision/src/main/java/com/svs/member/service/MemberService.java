package com.svs.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.svs.member.api.dto.MemberDto.*;
import com.svs.member.domain.Member;
import com.svs.member.domain.type.AccountStatus;
import com.svs.member.repository.projection.*;
import com.svs.member.repository.projection.DefaultMemberProjection.MemberInfoProjection;
import com.svs.member.repository.projection.DefaultMemberProjection.MemberProjection;

public interface MemberService {

	boolean signUp(MemberSignUpRequestDto dto);


	List<MemberProjection> findAllByStatus(AccountStatus status);

	void deleteByUserId(String userId);

	
	Optional<Member> findByUserId(String userId);


	int checkMember(String id, String pw);


	Optional<MemberInfoProjection> MemberInfo(String id);


}
