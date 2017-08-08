package com.sfms.sfmsassist.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sfms.sfmsassist.entities.UserDetail;

@Component
public class CurrentUser {

	public UserDetail getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getPrincipal() instanceof UserDetail)		
			return (UserDetail)authentication.getPrincipal();
		else
			return null;
	}

	public void removeCurrentUser(HttpServletRequest request, HttpServletResponse response) {

		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		
		
		Cookie[] cookies = request.getCookies();
		String cookieName;
		String cookiePath;
		Cookie cookie;
		for(int i =0;i<cookies.length;i++) {
			cookieName = cookies[i].getName();
			cookie = new Cookie(cookieName, "");
			cookiePath = request.getContextPath();
			if (!StringUtils.hasLength(cookiePath)) {
				cookiePath = "/";
			}
			cookie.setPath(cookiePath);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	
		
	}
	
}

