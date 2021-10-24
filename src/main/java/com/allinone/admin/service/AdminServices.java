package com.allinone.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allinone.admin.model.AdminUser;
import com.allinone.admin.model.Field;
import com.allinone.admin.model.ProductField;
import com.allinone.admin.model.ProductFieldValues;
import com.allinone.admin.model.Values;
import com.allinone.admin.repository.AdminUserRepository;
import com.allinone.admin.repository.FieldsRepository;
import com.allinone.admin.repository.ProductFieldRepository;
import com.allinone.admin.repository.ProductFieldValuesRepository;
import com.allinone.admin.repository.ValuesRepository;
import com.allinone.model.business.BusinessTypes;
import com.allinone.repository.business.BusinessTypesRepository;

@Service
public class AdminServices 
{
	@Autowired
	private AdminUserRepository adminUserRepository;
	
	@Autowired
	private FieldsRepository fieldsRepository;
	
	@Autowired
	private ValuesRepository valuesRepository;
	
	@Autowired
	private ProductFieldValuesRepository productFieldValuesRepository;
	
	@Autowired
	private ProductFieldRepository productFieldRepository;
	
	@Autowired
	private BusinessTypesRepository businessTypesRepository;
	
	
	public AdminUser adminLogin(AdminUser user)
	{
		AdminUser us=adminUserRepository.findByAdUserMail(user.getAdUserMail());
		return us;
	}
	public AdminUser saveAdmin(AdminUser user)
	{
		AdminUser us=adminUserRepository.save(user);
		return us;
	}
	
	public List<Field> saveField(String[] fields)
	{
		List<Field>flist=new ArrayList<>();
				
		for(String f:fields)
		{
			List<Field>exFlist=fieldsRepository.findFieldByFieldName(f);
			if(exFlist!=null&&exFlist.size()<=0)
			{
				flist.add(fieldsRepository.save(new Field(f)));
			}
			else
			{
				flist.addAll(exFlist);
			}
			
		}
		return flist;
	}
	public List<Values> saveFieldValue(String[] values)
	{
		List<Values>vlist=new ArrayList<>();
		
		for(String f:values)
		{
			List<Values>exvlist=valuesRepository.findValuesByValueName(f);
			if(exvlist!=null&&exvlist.size()<=0)
			{
				vlist.add(valuesRepository.save(new Values(f)));
			}
			else
			{
				vlist.addAll(exvlist);
			}
		}
		return vlist;
	}
	
	public ProductFieldValues saveProductFieldsValues(ProductFieldValues pfv)
	{	
		
		ProductFieldValues pfvnew=new ProductFieldValues();
		
		ProductFieldValues pfvexist=productFieldValuesRepository.findProductFieldValueByfName(pfv.getField().getfName());
		if(pfvexist!=null)
		{
			pfvnew=pfvexist;
		}
		
		pfvnew.setField(pfv.getField());
		if(pfv.getValues()!=null&&pfv.getValues().size()>0) 
		{
			for(Values v:pfv.getValues())
			{
				Values v1=valuesRepository.findById(v.getvId()).get();
				//Values v1=valuesRepository.findValuesByKeyName(v.getvKey());
				Boolean f=true;
				for(Values v2:pfvnew.getValues())
				{
					if(v2.getvKey().equals(v1.getvKey()))
					{
						f=false;
					}
				}
				if(f)
				pfvnew.addValues(v1);
			}
		}
		return productFieldValuesRepository.saveAndFlush(pfvnew);
	}
	
	public ProductField saveProductFields(ProductField pf)
	{
		BusinessTypes btype=businessTypesRepository.findById(pf.getBusinessType().getBusinessTypeId()).get();
		if(btype==null)
		{
			throw new NullPointerException("Business Type Not Exist");
		}
		pf.setBusinessType(btype);
		ProductFieldValues pfv=productFieldValuesRepository.findById(pf.getProductFieldValues().getFieldValueId()).get();
		if(pfv==null)
		{
			throw new NullPointerException("Product Filed Value Not Exist");
		}
		ProductField pfnew=productFieldRepository.saveAndFlush(pf);
		return pfnew;
	}
	
	public List<ProductField>getProductFieldByBusiessTypeId(Long BusinessTypeId)
	{
		return productFieldRepository.findProductFieldByBusinessTypeId(BusinessTypeId);
	}
	
	public List<ProductField> saveAllFieldToAllBusinessType()
	{
		List<ProductField>pfList=new ArrayList<>();
		List<BusinessTypes> btypeList=businessTypesRepository.findAll();
		for(BusinessTypes btype:btypeList)
		{
			List<Field>flist= fieldsRepository.findAll();
			for(Field f:flist)
			{
				ProductFieldValues pfv=new ProductFieldValues(f,null,"Y") ;
				ProductFieldValues pfvnew=saveProductFieldsValues(pfv);
				ProductField pf=new ProductField(btype,pfvnew,"Y");
				ProductField pfnew=saveProductFields(pf);
				pfList.add(pfnew);
			}
		}
		return pfList;
	}
	
	
}
