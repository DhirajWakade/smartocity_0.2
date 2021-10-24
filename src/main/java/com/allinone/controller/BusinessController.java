package com.allinone.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.allinone.configuration.CustomPropertiesConfiguration;
import com.allinone.configuration.Response;
import com.allinone.exception.FieldRequiredException;
import com.allinone.model.BusinessDetails;
import com.allinone.model.BusinessmanUser;
import com.allinone.model.CustomerUser;
import com.allinone.model.SampleBusinessDetails;
import com.allinone.model.business.BusinessTypes;
import com.allinone.model.business.BusinessTypesWithProductType;
import com.allinone.model.customer.OrderDetails;
import com.allinone.model.product.MultiSizeProductPrice;
import com.allinone.model.product.ProductDetails;
import com.allinone.model.product.ProductMaster;
import com.allinone.model.product.ProductSizeType;
import com.allinone.model.product.ProductTypes;
import com.allinone.model.product.SampleProduct;
import com.allinone.notification.NotificationService;
import com.allinone.notification.model.BusinessManNotification;
import com.allinone.notification.model.CustomerNotification;
import com.allinone.repository.TaxRateMasterRepository;
import com.allinone.repository.business.BusinessTypesRepository;
import com.allinone.repository.product.ProductSizeTypeRepository;
import com.allinone.repository.product.ProductTypesRepository;
import com.allinone.services.BusinessDetailsService;
import com.allinone.services.BusinessServices;
import com.allinone.services.ImageProcessService;
import com.allinone.services.OrderServices;
import com.allinone.services.ProductServices;
import com.allinone.util.Util;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/business")
public class BusinessController {

	@Autowired
	private BusinessServices businessServices;

	@Autowired
	private BusinessDetailsService businessDetailsService;

	@Autowired
	private ProductTypesRepository productTypesRepository;

	@Autowired
	private ProductSizeTypeRepository productSizeTypeRepository;

	@Autowired
	private BusinessTypesRepository businessTypesRepository;

	@Autowired
	private TaxRateMasterRepository taxRateMasterRepository;

	@Autowired
	private ProductServices productServices;

	@Autowired
	private OrderServices orderServices;

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private ImageProcessService imageProcessService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> saveUser(@RequestBody BusinessmanUser user) throws Exception {
		Response<?> respone = null;
		BusinessmanUser umobile = businessServices.findByMobileNo(user.getMobileNo());
		if (umobile != null) {
			respone = new Response<CustomerUser>(null, "0", "Mobile No already Existed");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
		BusinessmanUser u = businessServices.save(user);
		if (u != null) {
			respone = new Response<BusinessmanUser>(u, "1", "Successfuly Registered");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<BusinessmanUser>(null, "0", "failed...try again");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/addNewBusiness", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> addNewBusiness(@RequestBody BusinessDetails bd) throws Exception {
		Response<?> respone = null;
		if (StringUtils.isEmpty(bd.getPanCardNo())) {
			throw new FieldRequiredException("Pan Card Should Not Empty");
		}
		BusinessDetails newBd = businessDetailsService.saveBusiness(bd);
		if (newBd != null) {
			respone = new Response<BusinessDetails>(newBd, "1", "Successfuly Added Business");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<BusinessmanUser>(null, "0", "failed...try again");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/HomeAPIBusiness/{businessManID}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> homeAPI(@PathVariable("businessManID") Long businessManID) throws Exception {
		Response<?> respone = null;
		Map<String, Object> map = new HashMap<String, Object>();
		BusinessmanUser businesman = businessServices.findById(businessManID);
		if (businesman == null) {
			throw new FieldRequiredException("Invalid Business man Id");
		}

		Set<BusinessDetails> businessDetailsList = businessDetailsService
				.findBusinessDetailsByBusinessManId(businessManID);
		businesman.setBusinessDetails(businessDetailsList);

		// map.put("businessManDetails", businesman);
		map.put("businessDetailsList", businessDetailsList);
		// map.put("productTypes", productTypesRepository.findAll());
		// map.put("productSizeTypes",productSizeTypeRepository.findAll());
		map.put("businessTypes", businessTypesRepository.findAll());

		List<BusinessTypes> btList = businessTypesRepository.findAll();
		List<BusinessTypesWithProductType> btWithPtList = new ArrayList<BusinessTypesWithProductType>();
		for (BusinessTypes bt : btList) {
			List<ProductTypes> ptList = productTypesRepository.findAllBusinessTypeId(bt.getBusinessTypeId());
			List<ProductSizeType> pst = productSizeTypeRepository.findAllBusinessTypeId(bt.getBusinessTypeId());
			BusinessTypesWithProductType btnew = new BusinessTypesWithProductType(bt.getBusinessTypeId(),
					bt.getBusinessTypeName(), bt.getIsGstNoRequired(), bt.getBusinessTypeCode(),
					bt.getIsMultiSelection(), bt.getIsProductSearchAllow(), ptList, pst);
			btWithPtList.add(btnew);
		}
		map.put("businessTypesWithProductType", btWithPtList);
		map.put("taxRates", taxRateMasterRepository.findAll());

		respone = new Response<Map>(map, "1", "Successfull");
		return new ResponseEntity<>(respone, HttpStatus.OK);

	}

	@RequestMapping(value = "/getBusinessDetails/{businessManID}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getBusinessDetails(@PathVariable("businessManID") Long businessManID)
			throws Exception {
		Response<?> respone = null;
		BusinessmanUser businesman = businessServices.findById(businessManID);

		if (businesman == null || StringUtils.isEmpty(businesman.getBmId())) {
			throw new FieldRequiredException("Invalid Business man Id");
		}
		Map<String,Set<BusinessDetails>>map=new HashMap<String, Set<BusinessDetails>>();
		Set<BusinessDetails> businessDetailsList = businessDetailsService
				.findBusinessDetailsByBusinessManId(businessManID);
		Set<BusinessDetails> realbusiness=new HashSet<>();
		Set<BusinessDetails> tempbusiness=new HashSet<>();
		for(BusinessDetails b:businessDetailsList)
		{
			if(BusinessDetails.BUSINESS_REAL.equalsIgnoreCase(b.getRealOrTemp()))
			{
				realbusiness.add(b);
			}
			else
			{
				tempbusiness.add(b);
			}
		}
		map.put("REAL", realbusiness);
		map.put("TEMP", tempbusiness);
		respone = new Response<Map<String,Set<BusinessDetails>>>(map, "1", "Successfull");
		return new ResponseEntity<>(respone, HttpStatus.OK);
	}

	@RequestMapping(value = "/getRentTenBusinessDetails", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getBusinessDetails() throws Exception {
		Response<?> respone = null;
		List<BusinessDetails> businessDetailsList = businessDetailsService.getRecentBusinessDetails();
		if (businessDetailsList != null)
			respone = new Response<List<BusinessDetails>>(businessDetailsList, "1", "Successfull");
		else
			respone = new Response<List<BusinessDetails>>(businessDetailsList, "0", "No business Found");
		return new ResponseEntity<>(respone, HttpStatus.OK);
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> addProduct(@RequestBody ProductDetails pd) throws Exception {
		Response<?> respone = null;
		if (pd.getProductMaster() == null) {
			throw new FieldRequiredException("Product Master Data not Available");
		}
		if (pd.getPrice() == null) {
			throw new FieldRequiredException("Product Price is null");
		}
		if (pd.getBusinessDetails() == null && pd.getBusinessDetails().getBusinessId() == null) {
			throw new FieldRequiredException("Business Details null");
		}

		ProductDetails newpd = productServices.saveProductDetails(pd);
		if (newpd != null) {
			respone = new Response<ProductDetails>(newpd, "1", "Successfuly Product");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<ProductDetails>(null, "0", "failed...try again");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/addProductNew", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> addProductNew(@RequestBody SampleProduct pd) throws Exception {
		Response<?> respone = null;
		/*
		 * if(pd.getProductMaster()==null) { throw new
		 * FieldRequiredException("Product Master Data not Available"); }
		 * if(pd.getPrice()==null) { throw new
		 * FieldRequiredException("Product Price is null"); }
		 * if(pd.getBusinessDetails()==null&&pd.getBusinessDetails().getBusinessId()==
		 * null) { throw new FieldRequiredException("Business Details null"); }
		 */
		ProductDetails newpd = productServices.saveProductDetailsNew(pd);
		if (newpd != null) {
			respone = new Response<ProductDetails>(newpd, "1", "Successfuly Product");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<ProductDetails>(null, "0", "failed...try again");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/addMultiSizeToProduct", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> addMultiSizeToProduct(@RequestBody SampleProduct pd) throws Exception {
		Response<?> respone = null;
		/*
		 * if(pd.getProductMaster()==null) { throw new
		 * FieldRequiredException("Product Master Data not Available"); }
		 * if(pd.getPrice()==null) { throw new
		 * FieldRequiredException("Product Price is null"); }
		 * if(pd.getBusinessDetails()==null&&pd.getBusinessDetails().getBusinessId()==
		 * null) { throw new FieldRequiredException("Business Details null"); }
		 */
		MultiSizeProductPrice newpd = productServices.addMultiSizetoProduct(pd);
		if (newpd != null) {
			respone = new Response<MultiSizeProductPrice>(newpd, "1", "Successfuly Product");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<MultiSizeProductPrice>(null, "0", "failed...try again");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
	}
/*
	@RequestMapping(value = "/uploadProductImg", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Response<?>> uploadProductImg(@RequestParam("file") MultipartFile mfile) throws Exception {
		Response<?> respone = null;
		String urlImg = new Util().uploadImage(mfile);
		if (urlImg == null || urlImg.equals("")) {
			respone = new Response<String>(null, "0", "Image not uploaded");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<String>(urlImg, "1", "Successfully uploaded");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}

	}
*/	
	@RequestMapping(value = "/uploadImage/{imageType}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Response<?>> uploadProductImg(@RequestParam("file") MultipartFile mfile,@PathVariable String imageType) throws Exception {
		Response<?> respone = null;
		String urlImg =imageProcessService.uploadImage(mfile,imageType);
		if (urlImg == null || urlImg.equals("")) {
			respone = new Response<String>(null, "0", "Image not uploaded");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<String>(urlImg, "1", "Successfully uploaded");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}

	}
/*
	@RequestMapping(value = "/uploadProductSubImgs", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Response<?>> uploadProductImg(@RequestParam("file") List<MultipartFile> mfile)
			throws Exception {
		Response<?> respone = null;
		List<String> ulrList = new ArrayList<>();
		for (MultipartFile m : mfile) {
			ulrList.add(new Util().uploadImage(m));
		}

		if (ulrList.size() == 0) {
			respone = new Response<String>(null, "0", "Image not uploaded");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<List<String>>(ulrList, "1", "Successfully uploaded");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}

	}
*/
	@RequestMapping(value = "/getProductByBusinessId/{businessId}/{pageNo}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> searchProduct(@PathVariable("businessId") Long businessId,
			@PathVariable("pageNo") Integer pageNO) {
		Response<?> respone = null;
		List<ProductDetails> prodcCatList = productServices.findProductDetailsByBusinessId(businessId, pageNO);
		if (prodcCatList == null) {
			respone = new Response<List<ProductDetails>>(null, "0", "Products Not Found");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<List<ProductDetails>>(prodcCatList, "1", "Successfully");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/editProduct", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> editProduct(@RequestBody ProductDetails pd) throws Exception {
		Response<?> respone = null;
		if (pd.getProductMaster() == null) {
			throw new FieldRequiredException("Product Master Data not Available");
		}
		if (pd != null && pd.getProductDetailId() == null) {
			throw new FieldRequiredException("Product DetailId is null");
		}
		if (pd.getPrice() == null) {
			throw new FieldRequiredException("Product Price is null");
		}
		if (pd.getBusinessDetails() == null && pd.getBusinessDetails().getBusinessId() == null) {
			throw new FieldRequiredException("Business Details null");
		}

		ProductDetails newpd = productServices.saveProductDetails(pd);
		if (newpd != null) {
			respone = new Response<ProductDetails>(newpd, "1", "Successfuly Product");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<ProductDetails>(null, "0", "failed...try again");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/myOrder/{businessId}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> myOrdes(@PathVariable("businessId") Long businessId) {
		Response<?> respone = null;
		BusinessDetails b = businessDetailsService.findBusinessDetailsById(businessId);
		if (b == null) {
			throw new FieldRequiredException("Invalid Business man Id");
		}
		List<OrderDetails> orList = orderServices.getOrderDetailsByBusinessDetailsId(businessId);
		if (orList == null) {
			respone = new Response<List<OrderDetails>>(null, "0", "Order not found");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<List<OrderDetails>>(orList, "1", "Successfully");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/notification/{businessManId}", method = RequestMethod.GET)
	public ResponseEntity<Response<?>> getBusinessnotification(@PathVariable Long businessManId) {
		Response<?> respone = null;
		List<BusinessManNotification> bsNList = notificationService.findBusinessNotifictioByReadStatus(businessManId,
				CustomerNotification.NOTIF_UN_READ_STATUS);
		if (bsNList == null) {
			respone = new Response<List<BusinessManNotification>>(bsNList, "0", "No Notifaction Found");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<List<BusinessManNotification>>(bsNList, "1", "Successfully");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/addNewBusinessNew", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> addNewBusiness(@RequestBody SampleBusinessDetails bd) throws Exception {
		Response<?> respone = null;
		String message = "";
		if (StringUtils.isEmpty(bd.getPanCard())) {
			// throw new FieldRequiredException("Pan Card Should Not Empty");
			message = "Pan Card Should Not Empty";
			respone = new Response<BusinessmanUser>(null, "0", message);
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
		if (StringUtils.isEmpty(bd.getBusinessmanUser().getBmId())) {
			message = "BusinessUser id should not Empty";
			respone = new Response<BusinessmanUser>(null, "0", message);
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}

		BusinessDetails newBd = businessDetailsService.saveBusinessNew(bd);
		if (newBd != null) {
			respone = new Response<BusinessDetails>(newBd, "1", "Successfuly Added Business");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<BusinessmanUser>(null, "0", "failed...try again");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/editNewBusiness", method = RequestMethod.POST)
	public ResponseEntity<Response<?>> editNewBusiness(@RequestBody SampleBusinessDetails bd) throws Exception {
		Response<?> respone = null;
		String message = "";
		if (StringUtils.isEmpty(bd.getBusinessId())) {
			// throw new FieldRequiredException("Pan Card Should Not Empty");
			message = "Business DetailsId Should Not Empty";
			respone = new Response<BusinessmanUser>(null, "0", message);
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
		/*
		 * if(StringUtils.isEmpty(bd.getBusinessmanUser().getBmId())) {
		 * message="BusinessUser id should not Empty"; respone=new
		 * Response<BusinessmanUser>(null,"0",message); return new
		 * ResponseEntity<>(respone,HttpStatus.OK); }
		 */
		BusinessDetails oldBd1 = businessDetailsService.findBusinessDetailsById(bd.getBusinessId());
		if (oldBd1 == null) {
			message = "BusinessId Details not found ";
			respone = new Response<BusinessmanUser>(null, "0", message);
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
		// String oldBdStatus=oldBd.getStatus();
		bd.setBusinessmanUser(oldBd1.getBusinessmanUser());
		BusinessDetails oldBd = businessDetailsService.covertSampleBDtoBD(bd);
		BusinessDetails newBd = businessDetailsService.editBusinessDetails(oldBd);
		if (newBd != null) {
			respone = new Response<BusinessDetails>(newBd, "1", "Successfuly Updated Business");
			return new ResponseEntity<>(respone, HttpStatus.OK);

		} else {
			respone = new Response<BusinessmanUser>(null, "0", "failed...try again");
			return new ResponseEntity<>(respone, HttpStatus.OK);
		}
	}

	
}
