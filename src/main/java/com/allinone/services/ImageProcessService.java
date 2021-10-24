package com.allinone.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.allinone.configuration.CustomPropertiesConfiguration;
import com.allinone.exception.FieldRequiredException;
import com.google.common.io.Files;

@Service
public class ImageProcessService {
		
	public static String uploadFolder = System.getProperty("user.dir");
	
	private final Logger log =LoggerFactory.getLogger(this.getClass());
	
	public String getUploadMainDirectory()
	{
		return CustomPropertiesConfiguration.uploadDirectory;
	}
	public String getUploadDirectory(String imageType)
	{
		if(imageType==null&&"".equalsIgnoreCase(imageType))
		{
			throw new FieldRequiredException("Image Type not Found");
		}
		
		if(CustomPropertiesConfiguration.BUSIENSS_USER_PROFILE.equalsIgnoreCase(imageType))
		{
			return CustomPropertiesConfiguration.businessUserProfile;
		}
		else if(CustomPropertiesConfiguration.CUSTOMER_USER_PROFILE.equalsIgnoreCase(imageType))
		{
			return CustomPropertiesConfiguration.customerUserProfile;
		}
		else if(CustomPropertiesConfiguration.PRODUCT_IMG.equalsIgnoreCase(imageType))
		{
			return CustomPropertiesConfiguration.productImg;
		}
		else if(CustomPropertiesConfiguration.BUSIENSS_ADDRESS_ADD_PROF.equalsIgnoreCase(imageType))
		{
			return CustomPropertiesConfiguration.businessAddressProf;
		}
		else if(CustomPropertiesConfiguration.BUSIENSS_BANK_ACC_PROF.equalsIgnoreCase(imageType))
		{
			return CustomPropertiesConfiguration.businessBankAccProf;
		}
		else 
		{
			return null;
		}
	}
	
	public String uploadImage(MultipartFile mfile,String imageType)
	{
		String path=uploadFolder+getUploadMainDirectory()+getUploadDirectory(imageType);	
		
		File f = new File(path);
		String cm=Long.valueOf(System.currentTimeMillis()).toString();
		String ext=Files.getFileExtension(mfile.getOriginalFilename());
		String fileNamechange=mfile.getOriginalFilename().substring(0,4).concat(cm).concat("."+ext);
		File convertfile = new File(path + "\\" + fileNamechange);
		try {
			if (!f.isDirectory()) {
				f.mkdirs();
			}
			convertfile.createNewFile();
			String fileName = convertfile.getName();
			
			FileOutputStream fout = new FileOutputStream(convertfile);
			fout.write(mfile.getBytes());
			fout.close();
			log.info("***Uploaded Image===="+getUploadDirectory(imageType)+"/"+fileName);
			return getUploadDirectory(imageType)+"/"+fileName;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
