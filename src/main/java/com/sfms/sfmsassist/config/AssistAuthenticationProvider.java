package com.sfms.sfmsassist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.sfms.sfmsassist.entities.UserDetail;
import com.sfms.sfmsassist.repository.UserDetailsRepository;

public class AssistAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			System.out.println("authenticate");
			UserDetail user = (UserDetail) authentication.getPrincipal();
			UserDetail userObj = validateUserInfo(user);
			System.out.println("authenticate out");
			System.out.println(userObj.getFirstName() + "---------------" + userObj.getEmployeeId());
			return new UsernamePasswordAuthenticationToken(userObj, userObj.getPassword());

			// return authentication;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	private UserDetail validateUserInfo(UserDetail user) {

		String userId = user.getTcsMailid();
		String password = user.getPassword();

		System.out.println(userId+"    "+password);
		/*
		 * UserDetail userDetails = new UserDetail();
		 * userDetails.setTcsMailid(userId); userDetails.setPassword(password);
		 */
		System.out.println("111111111111111111111111111");
		/*
		 * UserDetail userAccInfoFinalObj =
		 * userDetailsRepository.findAll(userDetails);
		 */

		UserDetail userAccInfoFinalObj = userDetailsRepository.findByTcsMailidAndPassword(userId, password);
		System.out.println("validate user");
		System.out.println(userAccInfoFinalObj);
		if (userAccInfoFinalObj == null) {
			throw new BadCredentialsException("User  not exists.");
		}

		System.out.println(
				userAccInfoFinalObj.getFirstName() + "++++++++++++++++++++" + userAccInfoFinalObj.getLastName());
		return userAccInfoFinalObj;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
