package com.svs.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.svs.member.domain.Member;
import com.svs.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
@Service("UserDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		Optional<Member> member =  memberRepository.findByUserId(userId);
		
		if (member.isEmpty()) throw new UsernameNotFoundException("UsernameNotFoundException");
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(member.get().getRoleName().toString()));
		AccountContext accountContext = new AccountContext(member.get(),roles);
		
		return accountContext;
	}

}
