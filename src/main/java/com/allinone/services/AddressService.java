package com.allinone.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allinone.model.AddressDetails;
import com.allinone.repository.AddressDetailsRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressDetailsRepository addressDetailsRepository;
	
	public AddressDetails getAddressById(Long addressId)
	{
		Optional<AddressDetails> a=addressDetailsRepository.findById(addressId);
		return a.isPresent()?a.get():null;
	}

}
