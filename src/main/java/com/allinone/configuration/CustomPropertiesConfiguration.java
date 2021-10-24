package com.allinone.configuration;

public class CustomPropertiesConfiguration {
	
	public static final String BUSIENSS_USER_PROFILE="BUSIENSS_USER_PROFILE";
	public static final String BUSIENSS_BANK_ACC_PROF="BUSIENSS_BANK_ACC_PROF";
	public static final String BUSIENSS_ADDRESS_ADD_PROF="BUSIENSS_ADDRESS_ADD_PROF";
	public static final String CUSTOMER_USER_PROFILE="CUSTOMER_USER_PROFILE";
	public static final String PRODUCT_IMG="PRODUCT_IMG";
	
	//@Value("${UPLOAD_DIRECTORY_PATH:/webapps/Smartocity}")
	public static final String uploadDirectory="/webapps/Smartocity";
	
	//@Value("${UPLOAD_BUSINESS_USER_PROFILE:/business/user_profile}")
	public static final String  businessUserProfile="/business/user_profile";
	
	//@Value("${UPLOAD_BUSINESS_BANK_ACCT_PROF:/business/bank/account_prof}")
	public static final String businessBankAccProf="/business/bank/account_prof";
	
	//@Value("${UPLOAD_BUSINESS_ADDRESS_ADRS_PROF:/business/address/address_prof}")
	public static final  String businessAddressProf="/business/address/address_prof";
	
	//@Value("${UPLOAD_CUSTOMER_USER_PROFILE:/customer/user_profile}")
	public static final  String customerUserProfile="/customer/user_profile";
	
	//@Value("${UPLOAD_PRODUCT_PRODUCT_IMG:/product/product_img}")
	public static final  String productImg="/product/product_img";

	

	
	
	
}
