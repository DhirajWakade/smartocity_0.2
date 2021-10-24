package com.allinone.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.allinone.controller.RestController;
import com.allinone.model.BusinessDetails;
import com.allinone.model.business.BusinessFieldMaster;
import com.allinone.model.business.BusinessFieldsMapper;
import com.allinone.model.business.BusinessTypes;
import com.allinone.model.business.BusinessTypesWithProductType;
import com.allinone.model.product.ProductTypes;
import com.allinone.repository.BusinessmanRepository;
import com.allinone.repository.CustomerRepository;
import com.allinone.repository.business.BusinessTypesRepository;
import com.allinone.repository.product.ProductTypesRepository;
import com.allinone.services.BusinessDetailsService;

@Controller
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	private BusinessTypesRepository businessTypesRepository;
	@Autowired
	private ProductTypesRepository productTypesRepository;
	@Autowired
	private BusinessmanRepository businessmanRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BusinessDetailsService businessDetailsService;
	
	@RequestMapping("/")
	public String login() throws IOException
	{
		return "/login";
	}
	@RequestMapping("/index")
	public ModelAndView index()
	{
		ModelAndView model = new ModelAndView("index");
		model.addObject("businessManCount", businessmanRepository.findAll().size());
		model.addObject("businessDetailsCount", businessTypesRepository.findAll().size());
		model.addObject("customerCount", customerRepository.findAll().size());
		return model;
	}
	@RequestMapping("/business_type")
	public ModelAndView getBusinessTypes()
	{
		ModelAndView model = new ModelAndView("business_type");
		List<BusinessTypes>businessTypeList=businessTypesRepository.findAll();
		model.addObject("businessTypes", businessTypeList);
		return model;
	}
	@RequestMapping("/product-fields")
	public ModelAndView getProductFields()
	{
		ModelAndView model = new ModelAndView("product-field");
		List<BusinessTypes>businessTypeList=businessTypesRepository.findAll();
		model.addObject("businessTypes", businessTypeList);
		return model;
	}
	
	@RequestMapping("/product_types")
	public ModelAndView productTypes()
	{
		ModelAndView model = new ModelAndView("product_type");
		List<ProductTypes>ptypes=productTypesRepository.findAll();
		model.addObject("productTypes", ptypes);
		return model;
	}
	@RequestMapping("/businessFieldView")
	public ModelAndView businessFieldView()
	{
		ModelAndView model = new ModelAndView("business_fields");
		List<BusinessFieldMaster>bf=businessDetailsService.getAllBusinessMasterField();
		
				model.addObject("businessMasterField", bf);
		return model;
	}
	@RequestMapping("/businessFieldMap")
	public ModelAndView businessFieldMap()
	{
		ModelAndView model = new ModelAndView("business_fields");
		List<BusinessFieldMaster>bf=businessDetailsService.getAllBusinessMasterField();
		
				model.addObject("businessMasterField", bf);
		return model;
	}
	
	
	@RequestMapping("/business_fields")
	public ModelAndView business_fields()
	{
		ModelAndView model = new ModelAndView("business_fields");
		List<BusinessTypes>businessTypeList=businessTypesRepository.findAll();
		List<BusinessFieldsMapper>bf=businessDetailsService.getBusinessFieldByBusiessType(1L);
		model.addObject("businessTypes",businessTypeList);
		model.addObject("businessFiledMap",bf);
		//model.addObject("businessDetailsCount", businessTypesRepository.findAll().size());
		//model.addObject("customerCount", customerRepository.findAll().size());
		return model;
	}
	@RequestMapping("/business_details")
	public ModelAndView business_details()
	{
		ModelAndView model = new ModelAndView("business_details");
		List<BusinessDetails> businessDetailsList= businessDetailsService.getRecentBusinessDetails();
		List<BusinessTypes>businessTypeList=businessTypesRepository.findAll();
		List<BusinessFieldsMapper>bf=businessDetailsService.getBusinessFieldByBusiessType(1L);
		model.addObject("businessTypes",businessTypeList);
		model.addObject("businessFiledMap",bf);
		model.addObject("businessDetailsList",businessDetailsList);
		//model.addObject("businessDetailsCount", businessTypesRepository.findAll().size());
		//model.addObject("customerCount", customerRepository.findAll().size());
		return model;
	}
	
	@RequestMapping("/business_details/{businessDetailsID}")
	public ModelAndView business_details(@PathVariable("businessDetailsID") Long businessID)
	{
		ModelAndView model = new ModelAndView("business_details_view");
		BusinessDetails businessDetails= businessDetailsService.findBusinessDetailsById(businessID);
		List<BusinessTypes>businessTypeList=businessTypesRepository.findAll();
		
		model.addObject("businessTypes",businessTypeList);
		model.addObject("businessDetails",businessDetails);
		//model.addObject("businessDetailsCount", businessTypesRepository.findAll().size());
		//model.addObject("customerCount", customerRepository.findAll().size());
		return model;
	}
	
	
}
