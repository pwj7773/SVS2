package com.svs.security.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.svs.member.domain.Member;


public class AccountContext extends User {
	private Member member;
	
	public AccountContext(Member member,
			Collection<? extends GrantedAuthority> authorities) {
		super(member.getUserId(),member.getUserPw(), authorities);
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	
}
