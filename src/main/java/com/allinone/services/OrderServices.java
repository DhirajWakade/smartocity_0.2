package com.allinone.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allinone.configuration.Response;
import com.allinone.constants.Constants;
import com.allinone.exception.FieldRequiredException;
import com.allinone.model.AddressDetails;
import com.allinone.model.BusinessDetails;
import com.allinone.model.CustomerUser;
import com.allinone.model.customer.CartProduct;
import com.allinone.model.customer.MainOrder;
import com.allinone.model.customer.OrderDetails;
import com.allinone.model.customer.OrderProduct;
import com.allinone.model.sample.ViewCart;
import com.allinone.model.sample.ViewCartBusinessDetails;
import com.allinone.notification.NotificationConfiguration;
import com.allinone.notification.NotificationService;
import com.allinone.notification.PushNotificationService;
import com.allinone.notification.model.BusinessManNotification;
import com.allinone.notification.model.CustomerNotification;
import com.allinone.repository.customer.MainOrderRepository;
import com.allinone.repository.customer.OrderDetailsRepository;
import com.allinone.repository.customer.OrderProductRepository;

@Service
public class OrderServices 
{
	Logger logger = LoggerFactory.getLogger(OrderServices.class);
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepositoy;
	
	@Autowired
	private OrderProductRepository orderProductRepository;
	
	@Autowired
	private MainOrderRepository mainOrderRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private BusinessDetailsService businessDetailService; 
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private NotificationConfiguration notificationConfiguration;
	
	@Autowired
	private ProductServices productServices;
	
	public String getOrderCode()
	{
		Integer count=orderDetailsRepositoy.findCount();
		count++;
		return Constants.ORDER_SUB_CODE+count;
	}
	public String getMainOrderCode()
	{
		Integer count=mainOrderRepository.findCount();
		count++;
		return Constants.ORDER_CODE+count;
	}
	
	public OrderDetails saveOrderDetails(OrderDetails orderd)
	{
		Long customerId=orderd.getCustomer().getCustomerId();
		CustomerUser customer=null;
		if (customerId == null) {
			throw new FieldRequiredException("Customer Id Should not null");
		} else {
			customer = customerService.findByCustomerId(customerId);
		}
		logger.info("***OrderServices=>saveOrderDetails started***CustomerID = "+customerId);
		orderd.setOrderStatus(Constants.ORDER_STATUS_CREATED);
		orderd.setOrderCode(getOrderCode());
		orderd.setCustomer(customer);
		orderd.setOrderProducts(saveOrderProduct(orderd.getOrderProducts()));
		OrderDetails placeOrder=orderDetailsRepositoy.save(orderd);
		logger.info("***OrderServices=>saveOrderDetails End***Created order = "+placeOrder.getOrderCode());
		return placeOrder;
		
	}
	public List<OrderProduct> saveOrderProduct(List<OrderProduct>orderProducts)
	{
		try
		{
			List<OrderProduct>savedOrderProducts=new ArrayList<>();
			for(OrderProduct orderProduct:orderProducts) {
				OrderProduct newOp=orderProductRepository.save(orderProduct);
				savedOrderProducts.add(newOp);
			}
			return savedOrderProducts;
		}catch (Exception e) {			
			logger.error("*****failed in saveOrderProduct****",e);
			return null;
		}
	}
	
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Response<?>> placeOrder(ViewCart vc)
	{
		try
		{
			HttpStatus httpstatus=HttpStatus.BAD_REQUEST;
			Response<?> respone=null;
			List<ViewCartBusinessDetails>vcBusinessDetail=vc.getBusinessDetails();
			if(vcBusinessDetail==null) {
				respone=new Response<Object>(null,"0","Business Details Null in placeOrder");
			return new ResponseEntity<>(respone,httpstatus);
			}
			if(vcBusinessDetail==null) {
				respone=new Response<Object>(null,"0","Business Details Null in placeOrder");
				return new ResponseEntity<>(respone,httpstatus);
			}
			if(vcBusinessDetail.size()==0) {
				respone=new Response<Object>(null,"0","No Business Found in placeOrder");
				return new ResponseEntity<>(respone,httpstatus);
			}
			if(vc.getCustomer().getCustomerId()==null) {
				respone=new Response<Object>(null,"0","CustomerId should not Empty in placeOrder");
				return new ResponseEntity<>(respone,httpstatus);
			
			}
			if(vc.getOrderAddress().getAddrId()==null) {
				respone=new Response<Object>(null,"0","Customer Address should not Empty in placeOrder");
				return new ResponseEntity<>(respone,httpstatus);
			}
			
			
			CustomerUser customer=customerService.findByCustomerId(vc.getCustomer().getCustomerId());
			List<OrderDetails>placedSubOrders=new ArrayList<OrderDetails>();
			AddressDetails adress=addressService.getAddressById(vc.getOrderAddress().getAddrId());
			List<BusinessDetails>blist=new ArrayList<BusinessDetails>();
			for(ViewCartBusinessDetails vcbd:vcBusinessDetail)
			{
				if(vcbd.getBusinessId()==null) {
					respone=new Response<Object>(null,"0","Business details should not Empty in placeOrder");
					return new ResponseEntity<>(respone,httpstatus);
				}
				BusinessDetails bd=businessDetailService.findBusinessDetailsById(vcbd.getBusinessId());
				blist.add(bd);
				List<CartProduct>cartProducts=vcbd.getCartProducts();
				List<OrderProduct> opList=convertCartProductToOrderProduct(cartProducts);
				OrderDetails od=new OrderDetails();
				od.setCustomer(customer);
				od.setOrderProducts(opList);
				od.setAddress(adress);
				od.setBusinessDetail(bd);
				OrderDetails newOd=saveOrderDetails(od);
				logger.info("Customer="+customer.getCustomerUsername()+" placed SubOrder Code="+newOd.getOrderCode());
				placedSubOrders.add(newOd);
			}
			MainOrder mOrder=new MainOrder();
			mOrder.setCustomer(customer);
			mOrder.setOrderDetails(placedSubOrders);
			MainOrder mOrderPlaced=createMainOrder(mOrder);
			
			sendOrderNotifction(customer,blist,mOrder);
			
			respone=new Response<Object>(mOrderPlaced,"1","Successful");
			httpstatus=HttpStatus.OK;
			productServices.removeCardProduct(customer.getCustomerId());
			return new ResponseEntity<>(respone,httpstatus);
		}
		catch (Exception e) 
		{
			logger.error("*****placeOrder failed*****",e);
			return new ResponseEntity<>(new Response<Object>(null,"0","Failded"),HttpStatus.BAD_REQUEST);
		}
	}
	
	public List<OrderProduct> convertCartProductToOrderProduct(List<CartProduct> cartProducts)
	{
		List<OrderProduct>opList=new ArrayList<OrderProduct>();
		for(CartProduct cp:cartProducts)
		{
			OrderProduct op=new OrderProduct();
			op.setProductDetails(cp.getProductDetails());
			op.setQuantity(cp.getQuantity());
			op.setTotalPrice(cp.getTotalAmount());
			opList.add(op);
		}
		return  opList;
	}
	
	public MainOrder createMainOrder(MainOrder mOrder)
	{
		mOrder.setMainOrderCode(getMainOrderCode());
		mOrder.setMainOrderStatus(Constants.ORDER_STATUS_CREATED);
		MainOrder newMOrder=mainOrderRepository.save(mOrder);
		logger.info("Customer="+mOrder.getCustomer().getCustomerUsername()+" Placed mainOrder="+mOrder.getMainOrderCode());
		return  newMOrder;
		
	}
	
	public List<MainOrder> getOrderByCustomerId(Long customerId)
	{
		CustomerUser customer=customerService.findByCustomerId(customerId);
		if(customer!=null)
		return mainOrderRepository.findMainOrderByCustID(customerId);
		else
			return null;
	}
	
	public List<OrderDetails> getOrderDetailsByBusinessDetailsId(Long BusinessDetailId)	
	{
		if(BusinessDetailId==null)
			return null;
		else
		return orderDetailsRepositoy.findOrderDetailsByBusinessId(BusinessDetailId);
	}
	
	public void sendOrderNotifction(CustomerUser customer,List<BusinessDetails>list,MainOrder mOrder )
	{
		String cust_text=notificationConfiguration.getCustomerPlaceOrder();
		cust_text=cust_text.replace("MAIN_ORDER_NO", mOrder.getMainOrderCode());
		String busi_text=notificationConfiguration.getBusinessReceiveOrder();
		CustomerNotification cn=new CustomerNotification(customer,cust_text);
		notificationService.saveCustomerNotification(cn);
		List<OrderDetails>orderList=mOrder.getOrderDetails();
		for(OrderDetails order:orderList)
		{
			BusinessDetails busiDetails=order.getBusinessDetail();
			busi_text=busi_text.replace("ORDER_NO", order.getOrderCode());
			busi_text=busi_text.replace("BUSI_NAME",busiDetails.getBusinessName());
			busi_text=busi_text.replace("CUST_NAME",customer.getFirstName()+" "+customer.getLastName());
			BusinessManNotification bms=new BusinessManNotification(busiDetails.getBusinessmanUser(),busiDetails,busi_text);
			notificationService.saveBusinessNotification(bms);			
			
		}
		
		
	}

}
