package com.allinone.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allinone.admin.service.AdminServices;
import com.allinone.configuration.Response;
import com.allinone.model.AddressDetails;
import com.allinone.model.CustomerUser;
import com.allinone.model.business.BusinessCategory;
import com.allinone.model.customer.Cart;
import com.allinone.model.customer.MainOrder;
import com.allinone.model.product.ProductDetails;
import com.allinone.model.sample.AddAddressToCustomer;
import com.allinone.model.sample.AddToCart;
import com.allinone.model.sample.RemoveFromCart;
import com.allinone.model.sample.ViewCart;
import com.allinone.notification.NotificationService;
import com.allinone.notification.PushNotificationService;
import com.allinone.notification.model.CustomerNotification;
import com.allinone.notification.model.Notification;
import com.allinone.repository.business.BusinessCategoryRepository;
import com.allinone.repository.product.ProductDetailsRepository;
import com.allinone.services.BusinessServices;
import com.allinone.services.CustomerService;
import com.allinone.services.OrderServices;
import com.allinone.services.ProductServices;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductDetailsRepository productDetailsRepository;
	
	@Autowired
	private AdminServices adminServices;
	
	@Autowired
	private OrderServices orderService;
	
	@Autowired
	private BusinessServices businessService;
	
	@Autowired
	private ProductServices productServices;
	
	@Autowired
	private BusinessCategoryRepository businessCategoryRepository;
	
	@Autowired
	private PushNotificationService pushNotificationService;
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> saveUser(@RequestBody CustomerUser user) throws Exception 
	{
		Response<?> respone=null;
		CustomerUser umobile=customerService.findByMobileNo(user.getMobileNo());
		if(umobile!=null)
		{
			respone=new Response<CustomerUser>(null,"0","Mobile No already Existed");
			return new ResponseEntity<>(respone,HttpStatus.OK);
		}
		CustomerUser u=customerService.save(user);
		if (u != null) 
		{
			respone=new Response<CustomerUser>(u,"1","Successfuly Registered");
			return new ResponseEntity<>(respone,HttpStatus.OK);
					
		} else {
			respone=new Response<CustomerUser>(null,"0","failed...try again");
			return new ResponseEntity<>(respone,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/searchProduct/{desc}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> searchProduct(@PathVariable("desc")String desc) 	
	{
		Response<?> respone=null;
			List<ProductDetails>prodcCatList=productDetailsRepository.searchProductDetails(desc);
			if (prodcCatList== null) 
			{
				respone=new Response<List<ProductDetails>>(null,"0","Products Not Found");
				return new ResponseEntity<>(respone,HttpStatus.OK);
						
			} else {
				respone=new Response<List<ProductDetails>>(prodcCatList,"1","Successfully");
				return new ResponseEntity<>(respone,HttpStatus.OK);
			}			
	}
	
	@RequestMapping(value = "/HomeAPICustomer", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> homeAPI() throws Exception 
	{
		Response<?> respone=null;
		Map<String,Object>map=new HashMap<String, Object>();
		
		map.put("TopBusinessType", businessService.getActiveTopBusiness());
		map.put("OfferedProduct",productServices.activeOfferedproduct());
		
		respone=new Response<Map>(map,"1","Successfull");
		return new ResponseEntity<>(respone,HttpStatus.OK);
	}
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getCatogies() throws Exception 
	{
		Response<?> respone=null;
		List<BusinessCategory>bc=businessCategoryRepository.findAll();
		if (bc== null) 
		{
			respone=new Response<List<?>>(null,"0","Business Category Not Found");
			return new ResponseEntity<>(respone,HttpStatus.OK);
					
		} else {
			respone=new Response<List<?>>(bc,"1","Successfully");
			return new ResponseEntity<>(respone,HttpStatus.OK);
		}	
		
	}
	
	
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> placeOrder(@RequestBody ViewCart vc) throws Exception 
	{
		
		ResponseEntity<Response<?>> respone=orderService.placeOrder(vc);
		return respone;
	}
	
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> addToCart(@RequestBody AddToCart adtc) throws Exception 
	{
		Response<?> respone=null;
		Cart cart=customerService.addProductCart(adtc);
		if (cart== null) 
		{
			respone=new Response<Cart>(null,"0","Failed");
			return new ResponseEntity<>(respone,HttpStatus.OK);
					
		} else {
			respone=new Response<Cart>(cart,"1","Successfully");
			return new ResponseEntity<>(respone,HttpStatus.OK);
		}
		
	}
	@RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> removeFromCart(@RequestBody RemoveFromCart rm) throws Exception 
	{
		Response<?> respone=null;
		Cart cart=customerService.remoteProductFromCart(rm);
		ViewCart vcart=customerService.viewCartDetails(cart.getCustomer().getCustomerId());
		if (vcart== null) 
		{
			respone=new Response<ViewCart>(null,"0","Failed");
			return new ResponseEntity<>(respone,HttpStatus.INTERNAL_SERVER_ERROR);
					
		} else {
			respone=new Response<ViewCart>(vcart,"1","Successfully");
			return new ResponseEntity<>(respone,HttpStatus.OK);
		}
		
	}
	@RequestMapping(value = "/getCartDetails/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getCartDetails(@PathVariable("customerId") Long customerId) throws Exception 
	{
		Response<?> respone=null;
		ViewCart cart=customerService.viewCartDetails(customerId);
		if (cart== null) 
		{
			respone=new Response<ViewCart>(null,"0","Failed");
			return new ResponseEntity<>(respone,HttpStatus.OK);
					
		} else {
			respone=new Response<ViewCart>(cart,"1","Successfully");
			return new ResponseEntity<>(respone,HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/getProductByBusinessType/{businessTypeId}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getProductByBusinessType(@PathVariable("businessTypeId") Long businessTypeId) throws Exception 
	{
		Response<?> respone=null;
		List<ProductDetails>pdList=productServices.getProductByBusinessTypeId(businessTypeId);
		if (pdList.size()==0||pdList== null) 
		{
			respone=new Response<List<?>>(null,"0","Product Not Found");
			return new ResponseEntity<>(respone,HttpStatus.OK);
					
		}
		else {
			respone=new Response<List<?>>(pdList,"1","Successfully");
			return new ResponseEntity<>(respone,HttpStatus.OK);
		}	
		
	}
	@RequestMapping(value = "/addCustomerAddress", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> addCustomerAddress(@RequestBody AddAddressToCustomer aac)
	{
		Response<?> respone=null;
		AddressDetails ad=customerService.saveCustomerAddress(aac);
		if (ad==null) 
		{
			respone=new Response<AddressDetails>(null,"0","Failed...to Address");
			return new ResponseEntity<>(respone,HttpStatus.OK);
					
		}
		else {
			respone=new Response<AddressDetails>(ad,"1","Successfully");
			return new ResponseEntity<>(respone,HttpStatus.OK);
		}
	}
	@RequestMapping(value = "/getCustomerAddress/{custId}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getCustomerAddress(@PathVariable Long custId)
	{
		Response<?> respone=null;
		List<AddressDetails> ad=customerService.getCustomerAddress(custId);
		if (ad==null) 
		{
			respone=new Response<AddressDetails>(null,"0","No Address Found");
			return new ResponseEntity<>(respone,HttpStatus.OK);
					
		}
		else {
			respone=new Response<List<AddressDetails>>(ad,"1","Successfully");
			return new ResponseEntity<>(respone,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/myOrders/{custId}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getOrderOfCustomer(@PathVariable Long custId)
	{
		Response<?> respone=null;
		List<MainOrder> mOLList=orderService.getOrderByCustomerId(custId);
		if (mOLList==null) 
		{
			respone=new Response<List<MainOrder>>(mOLList,"0","No Address Found");
			return new ResponseEntity<>(respone,HttpStatus.OK);
					
		}
		else {
			respone=new Response<List<MainOrder>>(mOLList,"1","Successfully");
			return new ResponseEntity<>(respone,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/notification/{custId}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getCustomernotification(@PathVariable Long custId)
	{
		Response<?> respone=null;
		List<CustomerNotification> custNList=notificationService.findCustomrNotifictioByReadStatus(custId,CustomerNotification.NOTIF_UN_READ_STATUS);
		if (custNList==null) 
		{
			respone=new Response<List<CustomerNotification>>(custNList,"0","No Notifaction Found");
			return new ResponseEntity<>(respone,HttpStatus.OK);
					
		}
		else {
			respone=new Response<List<CustomerNotification>>(custNList,"1","Successfully");
			return new ResponseEntity<>(respone,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/send/{custID}/{mess}", method = RequestMethod.GET)
	public ResponseEntity<String> send(@PathVariable("custID") Long custID,@PathVariable("mess") String mess)
	{	Response<?> respone=null;
		CustomerUser customer=customerService.findByCustomerId(custID);
		if(customer==null)
			respone=new Response<CustomerUser>(null,"0","Cusotmer Not found");
		Notification n=new Notification("Smartocity_App",mess,customer.getFCMId());
		
		return pushNotificationService.sendNotifaction(n);
	}
	
	
	

}
