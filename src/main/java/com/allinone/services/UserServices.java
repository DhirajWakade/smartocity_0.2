package com.allinone.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.allinone.model.BusinessmanUser;
import com.allinone.model.CustomerUser;
import com.allinone.model.User;
import com.allinone.repository.BusinessmanRepository;
import com.allinone.repository.CustomerRepository;
import com.allinone.repository.UserRepository;

@Service
public class UserServices implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BusinessmanRepository businessmanRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username)
	{
		CustomerUser cuser=null;
		String usname=null;
		String password=null;
		//User user=userRepository.findByUserName(username);
		BusinessmanUser buser=businessmanRepository.findByBusinessUsername(username);
		Boolean isUserPresent=false;
		if(buser!=null)
		{
			usname=buser.getBusinessUsername();
			password=buser.getPassword();
			isUserPresent=true;
		}
		else {
			cuser=customerRepository.findByCustomerUsername(username);
			if(cuser!=null) 
			{
			usname=cuser.getCustomerUsername();
			password=cuser.getPassword();			
			isUserPresent=true;
			}else{
				isUserPresent=false;	
			}
		}
		
		
		if (!isUserPresent) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(usname, password,
				new ArrayList<>());
	}

	public User save(User user) {
		//user.setPassword(bcryptEncoder.encode(user.getPassword()));
		//user.setUserName(user.getMobileNumber()+"_"+user.getUserType());
		return userRepository.save(user);
	}
	
	
}
