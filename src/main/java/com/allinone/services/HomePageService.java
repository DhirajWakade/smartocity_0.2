package com.allinone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allinone.constants.Constants;
import com.allinone.model.customer.HomePageMenu;
import com.allinone.model.customer.HomePageOffersDetails;
import com.allinone.repository.customer.HomePageMenuRepository;
import com.allinone.repository.customer.HomePageOffersDetailsRepository;

@Service
public class HomePageService {

	
	@Autowired
	private HomePageOffersDetailsRepository offersDetailsRepository;
	
	@Autowired
	private HomePageMenuRepository homePageMenuRepository;
	
	public String assingOfferCode()
	{
		Integer count=offersDetailsRepository.findCount();
		count++;
		return Constants.HOME_PAGE_OFR+count;
	}
	public HomePageOffersDetails saveOffersDetails(HomePageOffersDetails od)
	{
		od.setOfferNumber(assingOfferCode());
		od.setOfferStatus(HomePageOffersDetails.OFR_STATUS_ACTIVE);
		return offersDetailsRepository.save(od) ;
	}
	public HomePageMenu saveMenuDetails(HomePageMenu hpm)
	{
		hpm.setHpMenuNumber(assingMenuCode());
		hpm.setHpMenuStatus(HomePageMenu.MN_STATUS_INACTIVE);
		return homePageMenuRepository.save(hpm) ;
	}
	public String assingMenuCode()
	{
		Integer count=homePageMenuRepository.findCount();
		count++;
		return Constants.HOME_PAGE_MENU+count;
	}
}
