package com.svs.member.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.svs.member.domain.Member;
import com.svs.member.domain.type.AccountStatus;
import com.svs.member.repository.projection.DefaultMemberProjection.*;


public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByUserId(String userId);
	Optional<MemberInfoProjection> findInfoByUserId(String userId);
	
	

	List<MemberProjection> findAllByStatus(AccountStatus status);

	@Transactional
	void deleteByUserId(String userId);
}
