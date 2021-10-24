package com.allinone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableWebMvc
@EnableScheduling
@EnableJms
public class AllInOneApplication extends SpringBootServletInitializer{

	private final static Logger log =LoggerFactory.getLogger(AllInOneApplication.class);
	
	public static void main(String[] args) {
		
		SpringApplication.run(AllInOneApplication.class, args);
		log.info("*****Application Started Successfully******");
		
	}
	
}
