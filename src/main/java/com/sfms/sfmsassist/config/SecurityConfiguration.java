package com.sfms.sfmsassist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**", "/js/**","/css/**","/templates/**","/webjars/**","/applet/**");
	}
	
	
/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return true;
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		});
	}
	*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http
		.csrf().disable()
        .headers().disable()
		.authorizeRequests()
				.antMatchers("/registerUser").permitAll()
				/*.antMatchers("/resources*//**").permitAll()*/
		.anyRequest().authenticated()
		.and().
		formLogin().loginPage("/loginform").permitAll().

		and().
		addFilterBefore(createUserNamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).deleteCookies("JSESSIONID", "XSRF-TOKEN")
		.logoutSuccessHandler(getLogoutSuccessHandler())
		.and()
		.sessionManagement().maximumSessions(1).expiredUrl("/login").sessionRegistry(getSessionRegistry());
;

		
	}

	@Bean
	public LogoutSuccessHandler getLogoutSuccessHandler() {
		AssistSuccessHandler logoutSuccessHandler = new AssistSuccessHandler();
		logoutSuccessHandler.setDefaultTargetUrl("/logout/sfmslogout");
		return logoutSuccessHandler;
	}
	@Bean
	public UsernamePasswordAuthenticationFilter createUserNamePasswordAuthenticationFilter() throws Exception {
		
		System.out.println("11111");
		UsernamePasswordAuthenticationFilter filter=new SfmsAssistFilter();
		
		filter.getPasswordParameter();
		System.out.println("111");
		filter.setAuthenticationManager(authenticationManagerBean());// this is the user bean which has the list of authentication tokens which has the users details if that is authentiucated
		System.out.println("222");
	//	System.out.println(filter.getAuthenticationManager());
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login","POST"));
		filter.setAuthenticationSuccessHandler(getauthenticationSuccessHandler());
		filter.setAuthenticationFailureHandler(getauthenticationFailureHandler("/error"));
		filter.setSessionAuthenticationStrategy(getSessionAuthenticationStrategy());
		return filter;
		
	}


	@Bean
	@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
		System.out.println("authenticationManagerBean");
        List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
    	System.out.println("authenticationManagerBean");
        authenticationProviderList.add(createAuthenticationProvider());
        
        for (AuthenticationProvider authenticationProvider : authenticationProviderList) {
			System.out.println(authenticationProvider);
		}
        
        //this list will have all the data about the users...if they are present 
    	System.out.println("authenticationManagerBean");
        AuthenticationManager authenticationManager = new ProviderManager(authenticationProviderList);
        return authenticationManager;
    }
	
	@Bean 
	public AuthenticationSuccessHandler getauthenticationSuccessHandler() {
		return new SuccessHandler();
	}
	
	@Bean 
	public AuthenticationFailureHandler getauthenticationFailureHandler(String url) {
		return new FailureHandler(url);
	}
	

	@Bean 
	public AuthenticationProvider createAuthenticationProvider() {
		System.out.println("createAuthenticationProvider");
		return new AssistAuthenticationProvider(); // returns if the user exdists or not
	}
	
	
	@Bean
	public SessionAuthenticationStrategy getSessionAuthenticationStrategy() {
		List<SessionAuthenticationStrategy> sessionAuthenticationStrategyList = new LinkedList<>();
		
		sessionAuthenticationStrategyList.add(getConcurrentSessionControlAuthenticationStrategy());
		sessionAuthenticationStrategyList.add(getSessionFixationProtectionStrategy());
		sessionAuthenticationStrategyList.add(getRegisterSessionAuthenticationStrategy());
		
		CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy= new CompositeSessionAuthenticationStrategy(sessionAuthenticationStrategyList);
		
		return compositeSessionAuthenticationStrategy;
	}
	
	@Bean
	public ConcurrentSessionControlAuthenticationStrategy getConcurrentSessionControlAuthenticationStrategy() {
		ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(getSessionRegistry());
		concurrentSessionControlAuthenticationStrategy.setMaximumSessions(1);
		return concurrentSessionControlAuthenticationStrategy;
	}
	
	@Bean
	public SessionRegistry getSessionRegistry() {
		return new SessionRegistryImpl();
	}
	
	@Bean
	public SessionFixationProtectionStrategy getSessionFixationProtectionStrategy() {
		SessionFixationProtectionStrategy sessionFixationProtectionStrategy = new SessionFixationProtectionStrategy();
		return sessionFixationProtectionStrategy;
	}
	
	@Bean
	public RegisterSessionAuthenticationStrategy getRegisterSessionAuthenticationStrategy() {
		RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy = new RegisterSessionAuthenticationStrategy(getSessionRegistry());
		return registerSessionAuthenticationStrategy;
	}
		

}
