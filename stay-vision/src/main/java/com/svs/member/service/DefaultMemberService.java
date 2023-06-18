package com.svs.member.service;

import java.util.List;
import java.util.Optional;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.svs.member.api.dto.MemberDto.*;
import com.svs.member.domain.Member;
import com.svs.member.domain.type.AccountStatus;
import com.svs.member.repository.MemberRepository;
import com.svs.member.repository.projection.DefaultMemberProjection.*;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Slf4j
public final class DefaultMemberService implements MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	@Override
	public boolean signUp(MemberSignUpRequestDto dto) {
		Member member = Member.builder()
				.userId(dto.userId())
				.userPw(passwordEncoder.encode(dto.userPw()))
				.userName(dto.userName())
				.email(dto.email())
				.phone(dto.phone())
				.roleName(dto.roleName())
				.build();
		memberRepository.save(member);
		return true;
	}

	@Override
	public List<MemberProjection> findAllByStatus(AccountStatus status) {
		return memberRepository.findAllByStatus(status);
	}
	@Override
	public void deleteByUserId(String userId) {
		memberRepository.deleteByUserId(userId);
	}
	@Override
	public Optional<Member> findByUserId(String userId) {
		return memberRepository.findByUserId(userId);
	}

	@Override
	public int checkMember(String id, String pw) {
		boolean pwMatch = passwordEncoder.matches(pw, memberRepository.findByUserId(id).get().getUserPw());
		
		if(pwMatch) {
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public Optional<MemberInfoProjection> MemberInfo(String userId) {
		return memberRepository.findInfoByUserId(userId);
	}

}
