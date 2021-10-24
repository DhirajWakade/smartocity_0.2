package com.allinone.mailconfiguration;



import java.io.UnsupportedEncodingException;
 
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

 

 
@Service("mailService")
public class MailServiceImpl implements MailService {
	
	
	Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
 
	String websiteName="Smartocity.com";
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private OneTimePasswordRepository oneTimePasswordRepository;
    
 
    public void sendEmail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //mimeMessage.setContent("text/html");
        
 
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            
 
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(),websiteName));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());
 
            mailSender.send(mimeMessageHelper.getMimeMessage());
 
        } catch (MessagingException e) {
            
            logger.info(e.getMessage());
            logger.info(e.getCause().toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            logger.info(e.getCause().toString());
        }
    }

	@Override
	public String registerOtpEmail(String email) {
		
		String time=String.valueOf(System.currentTimeMillis());
		String opt=time.substring(time.length()-7,time.length()-1);
		opt="123456";
		
		Mail mail = new Mail();
        mail.setMailFrom("smartocity9@gmail.com");
        mail.setMailTo(email);
        mail.setMailSubject("Smartocity Registration OTP");
        
        mail.setMailContent("Thank you for initiating the registration process with Smartocity. \n\n To complete the process,Please enter below One Time Password.\n\n OTO : "+opt+"\n\n (Otp is valid for next 15 min) \n\nThanks\nwww.Smartocity.com");
       try {
        OneTimePassword existingotp=oneTimePasswordRepository.findByEmail(email);
        if(existingotp==null) {
        oneTimePasswordRepository.save(new OneTimePassword(opt,email));
        }
        else
        {
        	existingotp.setOpt(opt);
        	oneTimePasswordRepository.saveAndFlush(existingotp);
        }
		
		//sendEmail(mail);
		//logger.info("email Id="+email+"  opt="+opt);
		return opt;
       }
       catch(Exception e) {
    	   logger.info(e.getMessage());
           logger.info(e.getCause().toString());
    	   e.printStackTrace();
    	  
    	  return null;
       }
		
	}
 
}