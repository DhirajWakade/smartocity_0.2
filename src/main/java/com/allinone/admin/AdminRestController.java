package com.allinone.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allinone.admin.model.AdminUser;
import com.allinone.admin.model.Field;
import com.allinone.admin.model.ProductField;
import com.allinone.admin.model.ProductFieldValues;
import com.allinone.admin.model.SampleProductFields;
import com.allinone.admin.model.Values;
import com.allinone.admin.service.AdminServices;
import com.allinone.configuration.Response;
import com.allinone.exception.FieldRequiredException;
import com.allinone.model.business.BusinessFieldMaster;
import com.allinone.model.business.BusinessFieldsMapper;
import com.allinone.model.business.BusinessTypes;
import com.allinone.model.product.ProductDetails;
import com.allinone.model.product.ProductTypes;
import com.allinone.repository.BusinessmanRepository;
import com.allinone.repository.CustomerRepository;
import com.allinone.repository.business.BusinessTypesRepository;
import com.allinone.repository.product.ProductTypesRepository;
import com.allinone.services.BusinessDetailsService;

@RestController
@RequestMapping("/restAdmin")
public class AdminRestController 
{

	@Autowired
	private AdminServices service;
	
	@Autowired
	private BusinessDetailsService businessDetailsService;
	
	@Autowired
	private BusinessTypesRepository businessTypesRepository;
	@Autowired
	private ProductTypesRepository productTypesRepository;
	@Autowired
	private BusinessmanRepository businessmanRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(value = "/hello")
	public String helo()	
	{
		return "Hello admin";
	}
	
	@RequestMapping(value = "/saveAdmin", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> saveAdmin(@RequestBody AdminUser user) throws Exception 	
	{
		Response<?> respone=null;	
		AdminUser us=service.saveAdmin(user);
		if(us!=null)
		{
				respone=new Response<AdminUser>(us,"1","Successfuly Added");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			
		}
		else {
				respone=new Response<ProductDetails>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> adminLogin(@RequestBody AdminUser user) throws Exception 	
	{
		Response<?> respone=null;	
		AdminUser us=service.adminLogin(user);
		if(us!=null)
		{
			if(us.getAdUserMail().equals(user.getAdUserMail())&&us.getAdUserPass().equals(user.getAdUserPass()))
			{
				respone=new Response<AdminUser>(us,"1","Successfuly Login");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
			else
			{
				respone=new Response<AdminUser>(null,"0","Invalid Password");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		}
		else {
				respone=new Response<ProductDetails>(null,"0","User not found");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	
	@RequestMapping(value = "/saveFields", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> saveField(@RequestBody String[] fieldsAray) throws Exception 	
	{
		Response<?> respone=null;	
		List<Field> f=service.saveField(fieldsAray);
		if(f!=null)
		{
				respone=new Response<List<Field>>(f,"1","Successfuly Added Field");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			
		}
		else {
				respone=new Response<List<Field>>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	@RequestMapping(value = "/saveValues", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> saveValues(@RequestBody String[] vsAray) throws Exception 	
	{
		Response<?> respone=null;	
		List<Values> f=service.saveFieldValue(vsAray);
		if(f!=null)
		{
				respone=new Response<List<Values>>(f,"1","Successfuly Added Values");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			
		}
		else {
				respone=new Response<List<Values>>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	
	@RequestMapping(value = "/saveProductFieldValues", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> saveProductFieldValues(@RequestBody ProductFieldValues spf) throws Exception 	
	{
		Response<?> respone=null;	
		ProductFieldValues f=service.saveProductFieldsValues(spf);
		if(f!=null)
		{
				respone=new Response<ProductFieldValues>(f,"1","Successfuly");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			
		}
		else {
				respone=new Response<ProductFieldValues>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	@RequestMapping(value = "/saveProductField", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> saveProductField(@RequestBody ProductField pf) throws Exception 	
	{
		Response<?> respone=null;	
		ProductField f=service.saveProductFields(pf);
		if(f!=null)
		{
				respone=new Response<ProductField>(f,"1","Successfuly");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			
		}
		else {
				respone=new Response<ProductField>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	
	@RequestMapping(value = "/getProductFieldByBusiessTypeId/{businessTypeId}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getProductFieldByBusiessTypeId(@PathVariable("businessTypeId") Long businessTypeId ) throws Exception 	
	{
		Response<?> respone=null;	
		List<ProductField> fList=service.getProductFieldByBusiessTypeId(businessTypeId);
		if(fList!=null)
		{
				respone=new Response<List<ProductField>>(fList,"1","Successfuly");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			
		}
		else {
				respone=new Response<ProductField>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	
	@RequestMapping(value = "/getBusinessField/{businessTypeId}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getBusinessFieldByBTId(@PathVariable("businessTypeId")Long businessTypeId) throws Exception 	
	{
		Response<?> respone=null;	
		List<BusinessFieldsMapper> fList=businessDetailsService.getBusinessFieldByBusiessType(businessTypeId);
		if(fList!=null)
		{
				respone=new Response<List<BusinessFieldsMapper>>(fList,"1","Successfuly");
				return new ResponseEntity<>(respone,HttpStatus.OK);
		}
		else {
				respone=new Response<BusinessFieldsMapper>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	@RequestMapping(value = "/mapAllFieldsToBusiness/{businessTypeId}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> mapAllFieldsToBusiness(@PathVariable("businessTypeId")Long businessTypeId) throws Exception 	
	{
		Response<?> respone=null;	
		Boolean flg=businessDetailsService.mapAllFieldToBusiness(businessTypeId);
		if(flg)
		{
				respone=new Response<Boolean>(flg,"1","Successfuly");
				return new ResponseEntity<>(respone,HttpStatus.OK);
		}
		else {
				respone=new Response<Boolean>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
		
	@RequestMapping(value = "/saveBusinessFieldMapper", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> saveBusinessFieldMapper(@RequestBody BusinessFieldsMapper bf) throws Exception 	
	{
		Response<?> respone=null;	
		BusinessFieldsMapper f=businessDetailsService.saveBusinessFieldMapper(bf);
		if(f!=null)
		{
				respone=new Response<BusinessFieldsMapper>(f,"1","Successfuly");
				return new ResponseEntity<>(respone,HttpStatus.OK);
		}
		else {
				respone=new Response<BusinessFieldsMapper>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	
	@RequestMapping(value = "/saveBusinessFieldToBusiness", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> MapBusinessFieldToBusiness(@RequestBody BusinessFieldsMapper[] bfm) throws Exception 	
	{
		Response<?> respone=null;	
		List<BusinessFieldsMapper> fList=new ArrayList<BusinessFieldsMapper>();
		for(BusinessFieldsMapper f:bfm)
		{
			BusinessFieldsMapper fNew=businessDetailsService.saveBusinessFieldMapper(f);
			fList.add(fNew);
		}
		if(fList!=null)
		{
				respone=new Response<List<BusinessFieldsMapper>>(fList,"1","Successfuly");
				return new ResponseEntity<>(respone,HttpStatus.OK);
		}
		else {
				respone=new Response<BusinessFieldsMapper>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	
	@RequestMapping(value = "/updateBusinessFieldToBusiness", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> updateBusinessFieldToBusiness(@RequestBody BusinessFieldsMapper bfm) throws Exception 	
	{
		Response<?> respone=null;	
		
			BusinessFieldsMapper fNew=businessDetailsService.updateBusinessFieldMapper(bfm);
		if(fNew!=null)
		{
				respone=new Response<BusinessFieldsMapper>(fNew,"1","Successfuly");
				return new ResponseEntity<>(respone,HttpStatus.OK);
		}
		else {
				respone=new Response<BusinessFieldsMapper>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	
	@RequestMapping(value = "/allProductType", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> allProductType() throws Exception 	
	{
		Response<?> respone=null;	
		List<ProductTypes>ptypes=productTypesRepository.findAll();
		if(ptypes!=null)
		{
				respone=new Response<List<ProductTypes>>(ptypes,"1","Successfuly");
				return new ResponseEntity<>(respone,HttpStatus.OK);
		}
		else {
				respone=new Response<List<ProductTypes>>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	@RequestMapping(value = "/allBusinessType", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> allBusinessType() throws Exception 	
	{
		Response<?> respone=null;	
		List<BusinessTypes>ptypes=businessTypesRepository.findAll();
		if(ptypes!=null)
		{
				respone=new Response<List<BusinessTypes>>(ptypes,"1","Successfuly");
				return new ResponseEntity<>(respone,HttpStatus.OK);
		}
		else {
				respone=new Response<List<BusinessTypes>>(null,"0","Failed Try Again");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}
		
	}
	
	
}



