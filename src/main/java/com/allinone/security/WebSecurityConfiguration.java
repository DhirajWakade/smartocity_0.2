package com.allinone.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.allinone.security.JwtAuthenticationEntryPoint;
import com.allinone.security.JwtRequestFilter;
import com.allinone.services.UserServices;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserServices userServices;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(userServices);//.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/*@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		String[] productionAccessUrl= {
				"/business/register","/customer/register",
				"/rest/login","/rest/**","/rest/checkMobileNo/*","/productImages/**","/productImages/*"
				,"/rest/getBusinessTypes,HomeAPI"
				,"/business/addProduct","/business/uploadProductImg"
				,"/business/addNewBusiness","/business/getBusinessDetails/*","/business/uploadProductSubImgs",
				"/customer/searchProduct/*","/admin/**","/customer/categories"
				};
		
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers(productionAccessUrl).permitAll().				
						anyRequest().authenticated().and().				
						exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	*/
	 @Override
	    protected void configure(HttpSecurity http) throws Exception{
	        http.authorizeRequests().antMatchers("/").permitAll();
	        http.csrf().disable();
	    }
	    

}
