package com.proxyseller.notes.config;

import com.proxyseller.notes.model.User;
import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {

	Authentication getAuthentication();
	User getCurrentUser();
}
