package com.allinone.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allinone.configuration.DatabaseImportClass;
import com.allinone.configuration.Response;
import com.allinone.mailconfiguration.MailService;
import com.allinone.mailconfiguration.OneTimePasswordService;
import com.allinone.model.BusinessmanUser;
import com.allinone.model.CustomerUser;
import com.allinone.model.business.BusinessTypes;
import com.allinone.model.product.ProductMaster;
import com.allinone.model.product.ProductSizeType;
import com.allinone.model.product.ProductTypes;
import com.allinone.model.security.JwtRequest;
import com.allinone.model.security.JwtResponse;
import com.allinone.repository.business.BusinessTypesRepository;
import com.allinone.repository.product.ProductSizeTypeRepository;
import com.allinone.repository.product.ProductTypesRepository;
import com.allinone.services.BusinessDetailsService;
import com.allinone.services.BusinessServices;
import com.allinone.services.CustomerService;
import com.allinone.services.ProductServices;
import com.allinone.services.SecurityService;
import com.allinone.services.UserServices;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BusinessServices businessServices;
	
	@Autowired
	private BusinessDetailsService businessDetailsService; 
	
	@Autowired
	private OneTimePasswordService oneTimePasswordService;
	
	@Autowired
	private ProductTypesRepository productTypesRepository;
	
	@Autowired
	private BusinessTypesRepository businessTypesRepository;
	
	@Autowired
	private ProductSizeTypeRepository productSizeTypeRepository;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private ProductServices productServices;
	
	@Autowired
	private  MailService mailService;
	
	@Autowired
	private DatabaseImportClass databaseImportClass;
	
	@RequestMapping(value="/hello",method = RequestMethod.GET)
	public String sayHello()
	{
		return "Hello ";
	}
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseEntity<?>createAuthenticationToken(@RequestBody JwtRequest jwtRequest)
	{
		Object user=null;
		String type=jwtRequest.getUserType().equalsIgnoreCase("CUSTOMER")?"C":"B";
		jwtRequest.setUsername(jwtRequest.getMobNo()+"_"+type);		
		String username=jwtRequest.getUsername();
		System.out.println("uer="+username);
		JwtResponse jwtResponce= securityService.authenticate(jwtRequest);
		String fcmId=jwtRequest.getFCMId();
		if("1".equalsIgnoreCase(jwtResponce.getStatus())) {
		if('C'==username.charAt(username.length()-1)) {
			CustomerUser cuser=customerService.findByCustomerUserName(username);
			user=(Object)cuser;
			customerService.UpdateFCMId(cuser.getCustomerId(),fcmId);
		}
		else
		{
			BusinessmanUser buser=businessServices.findByBusinessUsername(username);
			businessServices.UpdateFCMId(buser.getBmId(),fcmId);
			user=(Object)buser;
		}
		jwtResponce.setResult(user);
		}
		return ResponseEntity.ok(jwtResponce);		
	}
	
	@RequestMapping(value = "/checkMobileNo/{mobileNo}/{userType}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> checkMobileNo(@PathVariable("mobileNo")String mobileNo,@PathVariable String userType) throws Exception 
	{
		
		Boolean isMobNoPresent=false;
		Response<?>response=new Response<>();
		if("BUSINESS".equalsIgnoreCase(userType)) {
			BusinessmanUser bu=businessServices.findByMobileNo(mobileNo);			
			if(bu!=null)
			{			
				isMobNoPresent=true;
			}	
		}else if("CUSTOMER".equalsIgnoreCase(userType))
		{
			CustomerUser cu=customerService.findByMobileNo(mobileNo);	
			if(cu!=null)
			{
				isMobNoPresent=true;
			}
		}			
		
		if(isMobNoPresent) {
			response.setMessage("Mobile Number is Present");
			response.setStatus("0");	
		}
		else {
			
			response.setMessage("Mobile Number is not Present");
			response.setStatus("1");
		}
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/sendOTP/{emailId}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> sendOTP(@PathVariable("emailId")String emailId) throws Exception 
	{
		Response<String>response=new Response<>();
		String otp=oneTimePasswordService.sendOTP(emailId);
		
		if(otp!=null&&!otp.equalsIgnoreCase("")) {
			response.setMessage("OPT sent on email");
			response.setStatus("1");
			response.setResult(otp);
		}
		else {
			response.setMessage("Failed...Not Sent OTP");
			response.setStatus("0");
		}
		return ResponseEntity.ok(response);
	}
	/*
	@RequestMapping(value = "/getBusinessCategories", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response<?>> getBusinessCategories() throws Exception 
	{
		Response<List<BusinessCategory>>response=new Response<>();
		List<BusinessCategory>businessCategoryList=businessCategoryRepository.findAll();
		
		if(businessCategoryList!=null) {
			
			response.setMessage("Successfull");
			response.setStatus("1");
			response.setResult(businessCategoryList);
		}
		else {
			response.setMessage("Failed...Not Sent OTP");
			response.setStatus("0");
		}
		return ResponseEntity.ok(response);
	}*/
	@RequestMapping(value = "/getBusinessTypes", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response<?>> getBusinessTypes() throws Exception 
	{
		Response<List<BusinessTypes>>response=new Response<>();
		List<BusinessTypes>businessTypeList=businessTypesRepository.findAll();
		
		if(businessTypeList!=null) {
			
			response.setMessage("Successfull");
			response.setStatus("1");
			response.setResult(businessTypeList);
		}
		else {
			response.setMessage("Failed...Not Sent OTP");
			response.setStatus("0");
		}
		return ResponseEntity.ok(response);
	}
	@RequestMapping(value = "/getProductSizeTypes", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response<?>> getProductSizeTypes() throws Exception 
	{
		Response<List<ProductSizeType>>response=new Response<>();
		List<ProductSizeType>productSizeTypeList=productSizeTypeRepository.findAll();
		
		if(productSizeTypeList!=null) {
			
			response.setMessage("Successfull");
			response.setStatus("1");
			response.setResult(productSizeTypeList);
		}
		else {
			response.setMessage("Failed...Try Again");
			response.setStatus("0");
		}
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/getProductTypes", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response<?>> getProductTypes() throws Exception 
	{
		Response<List<ProductTypes>>response=new Response<>();
		List<ProductTypes>productSizeTypeList=productTypesRepository.findAll();
		
		if(productSizeTypeList!=null) {
			
			response.setMessage("Successfull");
			response.setStatus("1");
			response.setResult(productSizeTypeList);
		}
		else {
			response.setMessage("Failed...Try Again");
			response.setStatus("0");
		}
		return ResponseEntity.ok(response);
	}
	
	
	/*@RequestMapping(value = "/productCategory", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getProductCategory() 	
	{
		Response<?> respone=null;
			List<ProductCategory>prodcCatList=productServices.getProductCategory();
			if (prodcCatList== null) 
			{
				respone=new Response<List<ProductCategory>>(null,"0","Product Category Not Found");
				return new ResponseEntity<>(respone,HttpStatus.OK);
						
			} else {
				respone=new Response<List<ProductCategory>>(prodcCatList,"1","Successfully");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}			
			
	}
	@RequestMapping(value = "/productSubCategory", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getProductSubCategory() 	
	{
		Response<?> respone=null;
			List<ProductSubCategory>prodcCatList=productServices.getProductSubCategory();
			if (prodcCatList== null) 
			{
				respone=new Response<List<ProductSubCategory>>(null,"0","Product Sub Category Not Found");
				return new ResponseEntity<>(respone,HttpStatus.OK);
						
			} else {
				respone=new Response<List<ProductSubCategory>>(prodcCatList,"1","Successfully");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}			
			
	}
	@RequestMapping(value = "/productSubCategoryById/{productCatId}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getProductSubCategoryById(@PathVariable("productCatId")Long pcid ) 	
	{
		Response<?> respone=null;
			List<ProductSubCategory>prodcCatList=productServices.getProductSubCategory();
			if (prodcCatList== null) 
			{
				respone=new Response<List<ProductSubCategory>>(null,"0","Product Sub Category Not Found");
				return new ResponseEntity<>(respone,HttpStatus.OK);
						
			} else {
				respone=new Response<List<ProductSubCategory>>(prodcCatList,"1","Successfully");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}			
			
	}*/
	
	@RequestMapping(value = "/searchProduct/{productDesc}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> searchProduct(@PathVariable("productDesc")String productDesc ) 	
	{
		Response<?> respone=null;
			List<ProductMaster>prodcCatList=productServices.searchProductByDesc(productDesc);
			if (prodcCatList== null) 
			{
				respone=new Response<List<ProductMaster>>(null,"0","Products Not Found");
				return new ResponseEntity<>(respone,HttpStatus.OK);
						
			} else {
				respone=new Response<List<ProductMaster>>(prodcCatList,"1","Successfully");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}			
			
	}
	
	@RequestMapping(value = "/uploadDataBaseXML", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> uploadProductImg(@RequestBody LinkedList<String> tableName) throws Exception 	
	{
		Response<?> respone=null;
			//String[]tableNameArr=tableName.split(",");
		
			String flg=databaseImportClass.read(tableName);
			List<BusinessTypes>businessTypeList=businessTypesRepository.findAll();
			for(BusinessTypes bt:businessTypeList) {
			businessDetailsService.mapAllFieldToBusiness(bt.getBusinessTypeId());
			}
			if (flg!=null) 
			{
				respone=new Response<String>(flg,"1","Success");
				return new ResponseEntity<>(respone,HttpStatus.OK);
						
			} else {
				respone=new Response<String>(flg,"0","Failed");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}			
			
	}
	
	
	
	
}
