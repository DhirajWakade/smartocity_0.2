package com.allinone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.allinone.model.User;
import com.allinone.model.security.JwtRequest;
import com.allinone.model.security.JwtResponse;
import com.allinone.security.JwtTokenUtil;

@Service
public class SecurityService 
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public JwtResponse authenticate(JwtRequest jwtRequest)
	{
		String username=jwtRequest.getUsername();
		String password=jwtRequest.getPassword();
		UserDetails userDetails=null;
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			
		}
		catch (BadCredentialsException e) {
			//return new JwtResponse(null,"0","Invalid Credencial");
		}
		//return null;		
		userDetails=userServices.loadUserByUsername(username);
		if(userDetails!=null&userDetails.getPassword().equals(password)) {
		String token=jwtTokenUtil.generateToken(userDetails);
		return new JwtResponse(token,"1","Successfully Login");
	}
	else
	{
		return new JwtResponse("0","Invalid Credencial");
	}
		//String token=jwtTokenUtil.generateToken(userDetails);
		//return token;
		//return new JwtResponse(token,"1","Successfully Login");
	}
}
