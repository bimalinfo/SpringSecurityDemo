package com.aetna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private static String REALM = "MY_TEST_REALM";

	@Autowired
    private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("bill").password("{noop}abc123")
				.roles("ADMIN");
		auth.inMemoryAuthentication().withUser("tom").password("{noop}abc123")
				.roles("USER");
	}
	
	/*@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("bill").password("{noop}abc123")
				.roles("ADMIN");
		auth.inMemoryAuthentication().withUser("tom").password("{noop}abc123")
				.roles("USER");
		
		auth.inMemoryAuthentication()
		.withUser("bill").password(encoder().encode("abc123")).roles("ADMIN")
		.and()
		.withUser("tom").password(encoder().encode("abc123")).roles("USER");
	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/user/**")
				.hasRole("ADMIN").and().httpBasic().realmName(REALM)
				.authenticationEntryPoint(getBasicAuthEntryPoint()).and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);// We
																		// don't
																		// need
																		// sessions
																		// to be
																		// created.
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint();
	}
	
	

	// To allow Pre-flight [OPTIONS] request from browser 
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
	//New Impl
	@Bean
	public PasswordEncoder encoder() {
	return new BCryptPasswordEncoder();
	}
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication()
	.withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
	.and()
	.withUser("user").password(encoder().encode("userPass")).roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
	http
	.csrf().disable()
	.exceptionHandling()
	.authenticationEntryPoint(restAuthenticationEntryPoint)
	.and()
	.authorizeRequests()
	.antMatchers("/api/foos").authenticated()
	.antMatchers("/api/admin/**").hasRole("ADMIN")
	.and()
	.formLogin()
	.successHandler(mySuccessHandler)
	.failureHandler(myFailureHandler)
	.and()
	.logout();
	}*/
}
