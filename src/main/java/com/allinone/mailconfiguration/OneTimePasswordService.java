package com.allinone.mailconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OneTimePasswordService {
	
	@Autowired
	private OneTimePasswordRepository oneTimePasswordRepository;
	
	@Autowired
	private MailService mailService;
	
	public String sendOTP(String mailId)
	{
		return mailService.registerOtpEmail(mailId);
	}
	

}
