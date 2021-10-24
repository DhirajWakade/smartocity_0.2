package com.allinone.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.aspectj.apache.bcel.generic.FieldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allinone.exception.FieldRequiredException;
import com.allinone.model.AddressDetails;
import com.allinone.model.BankDetails;
import com.allinone.model.BusinessDetails;
import com.allinone.model.BusinessmanUser;
import com.allinone.model.PinCodeMaster;
import com.allinone.model.SampleBusinessDetails;
import com.allinone.model.business.BusinessFieldMaster;
import com.allinone.model.business.BusinessFieldsMapper;
import com.allinone.model.business.BusinessTypes;
import com.allinone.model.product.ProductDetails;
import com.allinone.repository.BusinessDetailsRepository;
import com.allinone.repository.business.BusinessFieldMasterRepository;
import com.allinone.repository.business.BusinessFieldsRepository;
import com.allinone.repository.business.BusinessTypesRepository;
import com.allinone.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class BusinessDetailsService {
	
	 private final Logger log =LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BusinessDetailsRepository businessDetailsRepository;
	
	@Autowired
	private BusinessTypesRepository businessTypesRepository;
	
	@Autowired
	private BusinessFieldMasterRepository businessFieldMasterRepository;
	
	@Autowired
	private BusinessServices businessServices;
	
	@Autowired
	private BusinessFieldsRepository businessFieldsRepository;
	
	
	public BusinessDetails saveBusiness(BusinessDetails businessDetails)
	{
		
		log.info("Save Business Info, BMID="+businessDetails.getBusinessmanUser().getBmId());
		try {
		Long businessManId=businessDetails.getBusinessmanUser().getBmId();
		
		if(businessManId==null)
		{
			throw new FieldRequiredException("Businessman Id is empty");
		}
		
		if(businessDetails.getBusinessType()==null)
		{
			throw new FieldRequiredException("Please select Business Type");
		}
		
		BusinessTypes businessType=businessTypesRepository.findById(businessDetails.getBusinessType().getBusinessTypeId()).get();
		if(businessType==null)
		{
			throw new FieldRequiredException("Invalid Business Type Id");
		}
		businessDetails.setBusinessType(businessType);
		BusinessmanUser existingbusinessman=businessServices.findById(businessManId);
		if(existingbusinessman==null)
		{
			throw new FieldRequiredException("Businessman is empty");
		}
		businessDetails.setBusinessmanUser(existingbusinessman);
		businessDetails.setStatus(BusinessDetails.CREATED_STATUS);
		businessDetails.setActiveDeactiveStatus(BusinessDetails.DEACTIVE_STATUS);
		businessDetails.setRealOrTemp(BusinessDetails.BUSINESS_TEMP);
		businessDetails.setBusinessCode("B"+businessDetailsRepository.findCount());
		BusinessDetails bd=businessDetailsRepository.save(businessDetails);
		log.info("Save Business Info, Successfully Add Business id="+bd.getBusinessId());
		return bd;
		}
		catch(Exception e)
		{
			log.error("Save Business Info...Failed...,"+e.getMessage());
			return null;
		}
		
	}
	
	public Set<BusinessDetails> findBusinessDetailsByBusinessManId(Long businessManId)
	{
		return businessDetailsRepository.findBusinessByBusinessManId(businessManId);
	}
	
	public BusinessDetails findBusinessDetailsById(Long businessId)
	{		
		Optional<BusinessDetails>bdOptionnal=businessDetailsRepository.findById(businessId);
		return bdOptionnal.isEmpty()?null:bdOptionnal.get();
	}
		
	public BusinessFieldsMapper saveBusinessFieldMapper(BusinessFieldsMapper bf)
	{
		BusinessTypes bff=bf.getBusinessType();
		if(bff==null) {
			throw new FieldRequiredException("BusinessType Should not be Empty");
		}
		Optional<BusinessTypes> bt=businessTypesRepository.findById(bff.getBusinessTypeId());
		if(!bt.isPresent())
			throw new FieldRequiredException("BusinessType Should not be Empty");
		
		
		if(bf.getBusinessField()==null)
			throw new FieldRequiredException("Business Field Master Should not be Empty");
		
		Optional<BusinessFieldMaster> bfmStr=businessFieldMasterRepository.findById(bf.getBusinessField().getfId());
		if(!bfmStr.isPresent())
			throw new FieldRequiredException("Business Field Master Should not be Empty");
		BusinessFieldsMapper existingMapper=businessFieldsRepository.findAllbybusinessTypeAndField(bff.getBusinessTypeId(),bf.getBusinessField().getfId());
		 if(existingMapper==null) {
			 try {
				 Long count=businessFieldsRepository.findCount();
				 bf.setFieldId(count+1);
		        BusinessFieldsMapper bs=businessFieldsRepository.save(bf);
		 return bs;
			 }
			 catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		 }else {
			 return existingMapper;
		 }
	}
	public BusinessFieldsMapper updateBusinessFieldMapper(BusinessFieldsMapper bf)
	{
		Optional<BusinessFieldsMapper> existingMapper=businessFieldsRepository.findById(bf.getFieldId());
		
		if(existingMapper.isPresent()) 
		{
			BusinessFieldsMapper newBf=existingMapper.get();
			newBf.setIsVisible(bf.getIsVisible());
			newBf.setMandaryOrOptional(bf.getMandaryOrOptional());
		 BusinessFieldsMapper bs=businessFieldsRepository.saveAndFlush(newBf);
		 return bs;
		 }else {
			 return null;
		 }
	}
	
	public Boolean updateBusinessFieldMapperAll(BusinessFieldsMapper[] bfmArray)
	{
		try {
			for(BusinessFieldsMapper bfm:bfmArray)
			{
				updateBusinessFieldMapper(bfm);
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	public Boolean mapAllFieldToBusiness(Long businessId)
	{
		try
		{
			Optional<BusinessTypes> btOpt=businessTypesRepository.findById(businessId);
			if(!btOpt.isPresent())
				throw new FieldRequiredException("BusinessType Should not be Empty");
			
			BusinessTypes bt=btOpt.get();
			
			List<BusinessFieldMaster> list=businessFieldMasterRepository.findAll();
			for(BusinessFieldMaster master:list)
			{
				BusinessFieldsMapper newBfm=new BusinessFieldsMapper(bt,master,BusinessFieldsMapper.IS_VISIBLE_YES,BusinessFieldsMapper.IS_OPTIONAL);
				saveBusinessFieldMapper(newBfm);
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}
		
	}
	
	
	
	/*public List<BusinessFieldMaster> saveBusinessFieldmaster(String[] master)
	{
		List<BusinessFieldMaster> list=new ArrayList<BusinessFieldMaster>();
		for(String str:master)
		{
			String code=Util.toUpperCaseWithSpaceReplace(str);
			BusinessFieldMaster bfmStr=businessFieldMasterRepository.findByFieldCode(code);
			if(bfmStr==null)
			{
				BusinessFieldMaster bfm=new BusinessFieldMaster(str);
				bfm.setfId(Long.valueOf(businessFieldMasterRepository.findCount()+1));
				BusinessFieldMaster bfmNew=businessFieldMasterRepository.save(bfm);
				list.add(bfmNew);
				log.info("Fid="+bfmNew.getfId()+" ,Code="+bfmNew.getFieldCode());
			}
		}
		return list;
	}*/
	
	public List<BusinessFieldMaster> saveBusinessFieldmaster(Map<Long,String>filedMap)
	{
		List<BusinessFieldMaster> list=new ArrayList<BusinessFieldMaster>();
		for(Map.Entry<Long,String>map:filedMap.entrySet())
		{
			if(map.getKey()!=null) {
			String code=Util.toUpperCaseWithSpaceReplace(map.getValue());
			BusinessFieldMaster bfmStr=businessFieldMasterRepository.findByFieldCode(code);
			if(bfmStr==null)
			{
				BusinessFieldMaster bfm=new BusinessFieldMaster(map.getValue());
				bfm.setfId(map.getKey());
				BusinessFieldMaster bfmNew=businessFieldMasterRepository.save(bfm);
				list.add(bfmNew);
				log.info("Fid="+bfmNew.getfId()+" ,Code="+bfmNew.getFieldCode());
			}
			}
		}
		return list;
	}
	
	public List<BusinessFieldsMapper> getBusinessFieldByBusiessType(Long businessTypeId)
	{
		Optional<BusinessTypes> bt=businessTypesRepository.findById(businessTypeId);
		if(!bt.isPresent())
			throw new FieldRequiredException("BusinessType Should not be Empty");
		List<BusinessFieldsMapper> bfList=businessFieldsRepository.findAllbybusinessType(businessTypeId);
		return bfList;
	}
	public List<BusinessFieldsMapper> getAllBusinessField()
	{
		List<BusinessFieldsMapper> bfList=businessFieldsRepository.findAll();
		return bfList;
	}
	public List<BusinessFieldMaster> getAllBusinessMasterField()
	{
		List<BusinessFieldMaster> bfList=businessFieldMasterRepository.findAll();
		return bfList;
	}

	public BusinessDetails saveBusinessNew(SampleBusinessDetails sbd) 
	{
		BusinessDetails bd=covertSampleBDtoBD(sbd);
		 return saveBusiness(bd);
	}
	public BusinessDetails covertSampleBDtoBD(SampleBusinessDetails sbd)
	{
		BusinessDetails bd=new BusinessDetails();
		bd.setBusinessName(sbd.getBusinessName());
		bd.setPanCardNo(sbd.getPanCard());
		bd.setBusinessNameAaPerPancard(sbd.getNameAsPerPan());
		bd.setGstNumber(sbd.getGstNumber());
		if(sbd.getBusinessmanUser()!=null)
		bd.setBusinessmanUser(sbd.getBusinessmanUser());
		bd.setBusinessType(sbd.getBusinessType());
		bd.setBusinessEmailId(sbd.getEmailId());
		bd.setMinAmtOrderExpected(sbd.getMinimumOrderAmount());
		bd.setShopImgUrl(sbd.getShopImageUrl());
		bd.setTermAndCondition(sbd.getTermAndCondition());
		bd.setProvideCashOnDelivery(sbd.getCashOnDelivery());
		bd.setFoodLicenseNumber(sbd.getFoodLicenseNumber());
		bd.setPanCardImgUrl(sbd.getPanCardImageUrl());
		bd.setCheckImageUrl(sbd.getCheckImageUrl());
		bd.setmOAWithCharges(sbd.getmOAWithCharges());
		bd.setDeliveryChargesPerKM(sbd.getDeliverPChargesPerKM());
		String pinstr="";
		if(sbd.getDeliverablePIN_Code()!=null) 
		{
		for(PinCodeMaster pin:sbd.getDeliverablePIN_Code())
		{
			pinstr=pinstr.concat(pin.getPinCode().toString()).concat(",");
		}
		pinstr=pinstr.substring(0, pinstr.length() - 1);   
		bd.setDeliverablePIN_Code(pinstr);}
		bd.setFreeDelivery(sbd.getFreeDelivery());
		
		//Date expDate=new java.util.Date(sbd.getFoodLicenseNumberExpiryDate());
		bd.setFoodLicenseNumberExpiryDate(Util.covertStringToSQLdate(sbd.getFoodLicenseNumberExpiryDate()));
		bd.setFoodLicenseImageUrl(sbd.getFoodLicenseImageUrl());
		
		AddressDetails addressDetails=new AddressDetails();
		 addressDetails.setAddreLine1(sbd.getAddressLine1());
		 addressDetails.setAddreLine2(sbd.getAddressLine2());
		 addressDetails.setCity(sbd.getCity());
		 addressDetails.setPinCode(sbd.getPincode());
		 addressDetails.setState(sbd.getState());
		 
		bd.setAddressDetails(addressDetails);
		
		BankDetails bank=new BankDetails();
		bank.setAccountNo(sbd.getAccountNo());
		bank.setIfscCode(sbd.getIfscCode());
		bank.setChequeOrPassbookImgUrl(sbd.getCheckImageUrl());
		bd.setBankDetails(bank);
		
		return bd;
	}
	
	public BusinessDetails editBusinessDetails(BusinessDetails bd)
	{
		log.info("***Edit Business Info Started***, BusinessId="+bd.getBusinessId());
		bd.setRealOrTemp(BusinessDetails.BUSINESS_TEMP);
		bd.setParentBusinessId(bd.getBusinessId());
		bd.setBusinessId(null);
		
		BusinessDetails updatedBD=saveBusiness(bd);
		
		log.info("***Edit Business Info END***,New BusinessId="+updatedBD.getBusinessId());
		return updatedBD;
	}
	
	public List<BusinessDetails>getRecentBusinessDetails()
	{
		return businessDetailsRepository.findAll();
	}
	

}
