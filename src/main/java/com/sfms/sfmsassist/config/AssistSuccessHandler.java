package com.sfms.sfmsassist.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.sfms.sfmsassist.entities.UserDetail;

public class AssistSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		if (authentication != null) {

			if (authentication.getPrincipal() instanceof UserDetail) {
				UserDetail userInfo = (UserDetail) authentication.getPrincipal();
				if (userInfo != null)

				{
		}

		super.onLogoutSuccess(request, response, authentication);
	}
	}
	}}

