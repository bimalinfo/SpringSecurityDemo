package com.security.resttemplate;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic().and().authorizeRequests()
        .antMatchers("/user").hasRole("USER")
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/adminlogin").hasRole("ADMIN")
        .antMatchers("/userlogin").hasRole("USER")
        .antMatchers("/allempdetails").hasRole("USER")
        .antMatchers("/allEmployeesUsingExchange").hasRole("USER")
        .antMatchers("/anEmployeeUsingExchange").hasRole("HR")
        .antMatchers("/anemployee").hasRole("HR")
        .antMatchers("/saveEmployee").hasRole("USER")
        .antMatchers("/createEmployee").hasRole("USER")
        .antMatchers("/saveEmployee").hasRole("USER")
        .anyRequest().authenticated()
        .and()
        .csrf().disable();
    }
  
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication()
        .withUser("user").password(passwordEncoder().encode("pwd")).roles("USER")
        .and()
        .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").and()
        .withUser("hr").password(passwordEncoder().encode("hr")).roles("HR");
    }
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and()
			.authorizeRequests()
				.antMatchers("/css/**", "/index").permitAll()		
				.antMatchers("/user/**").hasRole("USER")			
				.and().csrf().disable();
			.formLogin()
				.loginPage("/login").failureUrl("/login-error");	
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER");
	}*/


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}