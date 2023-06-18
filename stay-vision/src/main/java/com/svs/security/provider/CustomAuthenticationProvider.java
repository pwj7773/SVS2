package com.svs.security.provider;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.svs.member.domain.type.AccountStatus;
import com.svs.security.service.AccountContext;



public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String)authentication.getCredentials();

		AccountContext accountContext = (AccountContext)userDetailsService.loadUserByUsername(username);
		
		if (!passwordEncoder.matches(password, accountContext.getMember().getUserPw())) throw new BadCredentialsException("BadCredentialsException");
		if (!accountContext.getMember().getStatus().equals(AccountStatus.ACTIVE)) throw new BadCredentialsException("BadCredentialsException");
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountContext.getMember(),null,accountContext.getAuthorities());
		
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}


}
