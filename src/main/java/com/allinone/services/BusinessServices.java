package com.allinone.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allinone.model.BusinessmanUser;
import com.allinone.model.CustomerUser;
import com.allinone.model.User;
import com.allinone.model.business.BusinessCategory;
import com.allinone.model.business.BusinessTypes;
import com.allinone.repository.BusinessmanRepository;
import com.allinone.repository.UserRepository;
import com.allinone.repository.business.BusinessCategoryRepository;
import com.allinone.repository.business.BusinessTypesRepository;

@Service
public class BusinessServices {
	
	@Autowired
	private BusinessmanRepository businessmanRepository;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private BusinessTypesRepository businessTypesRepository;
	
	@Autowired
	private BusinessCategoryRepository businessCategoryRepository;

	public BusinessmanUser findByMobileNo(String mobile)
	{
		return businessmanRepository.findByMobileNo(mobile);
	}
	public BusinessmanUser save(BusinessmanUser u) {
		
		u.setBusinessUsername(u.getMobileNo()+"_B");
		User user=new User(u.getBusinessUsername(),u.getPassword(),"BUSINESS");
		userServices.save(user);
		return businessmanRepository.save(u);
		
	}

	@Transactional
	public BusinessmanUser update(BusinessmanUser u) {

		return businessmanRepository.save(u);

	}
	public BusinessmanUser findByBusinessUsername(String username) {		
		return businessmanRepository.findByBusinessUsername(username);		
	}
	
	public BusinessmanUser findById(Long userId)
	{
		Optional<BusinessmanUser>optinalBM=businessmanRepository.findById(userId);
		return optinalBM.isPresent()?optinalBM.get():null;
	}
	
	public List<BusinessTypes>getActiveTopBusiness()
	{
		return businessTypesRepository.findTopBusinessTypeByStatus("Y");
	}
	public BusinessmanUser UpdateFCMId(Long bmId,String fcmId)
	{
		BusinessmanUser bmUser=findById(bmId);
		bmUser.setFCMId(fcmId);
		return businessmanRepository.save(bmUser);
	}
	
	
	
}
