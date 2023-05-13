package com.proxyseller.notes.config;

import com.proxyseller.notes.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public User getCurrentUser() {
		return (User) getAuthentication().getPrincipal();
	}
}
