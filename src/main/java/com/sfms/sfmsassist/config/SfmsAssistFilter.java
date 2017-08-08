package com.sfms.sfmsassist.config;

import com.sfms.sfmsassist.entities.UserDetail;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

public class SfmsAssistFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			if (!request.getMethod().equals("POST")) {
				throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
			}

			String userName = request.getParameter("userName");
			String password = Base64.getEncoder().encodeToString(request.getParameter("password").getBytes());
			System.out.println("-----------------------base64!!!"+password+"-------------------");
			if (userName == null) {
				userName = "";
			}

			if (password == null) {
				password = "";
			}

			userName = userName.trim();

			UserDetail user = new UserDetail();
			user.setTcsMailid(userName);
			user.setPassword(password);
			System.out.println(userName + "" + password);


			System.out.println(this.getAuthenticationManager() + "dfdsfdsffdsfddffds");
			UsernamePasswordAuthenticationToken req = new UsernamePasswordAuthenticationToken(user, password);
			System.out.println(req.getPrincipal() + "");
			setDetails(request, req);
			System.out.println("!!!1");
			return this.getAuthenticationManager().authenticate(req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		// return null;
	}

}
